package ru.egordubina.mywater.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.egordubina.mywater.domain.usecases.DailyUseCase
import ru.egordubina.mywater.ui.uistates.SettingsUiState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dailyUseCase: DailyUseCase,
) : ViewModel() {
    val uiState = combine(
        dailyUseCase.getDailyData(),
        dailyUseCase.getGlassVolume()
    ) { daily, glassVolume ->
        SettingsUiState(dailyWaterValue = daily, glassVolume = glassVolume)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiState()
    )

    fun changeGlassVolume(glassVolume: Int) {
        viewModelScope.launch {
            dailyUseCase.updateGlassVolume(glassVolume = glassVolume)
        }
    }
}