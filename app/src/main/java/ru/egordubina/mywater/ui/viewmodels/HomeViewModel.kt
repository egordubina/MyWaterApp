package ru.egordubina.mywater.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.egordubina.mywater.ui.uistates.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                dailyWaterValue = 3000,
            )
        }
    }

    fun updateCurrent() {
        _uiState.update {
            it.copy(
                dailyWaterValue = 3000,
                currentWaterValue = it.currentWaterValue + 250
            )
        }
    }
}