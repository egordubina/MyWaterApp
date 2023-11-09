package ru.egordubina.mywater.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.egordubina.mywater.domain.usecases.DailyWaterUseCase
import ru.egordubina.mywater.ui.uistates.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dailyWaterUseCase: DailyWaterUseCase
) : ViewModel() {
//    private var _uiState = MutableStateFlow(HomeUiState())
//    val uiState = _uiState.asStateFlow()

    val uiState = combine(
        dailyWaterUseCase.getDailyWaterValue()
    ) {
        HomeUiState(dailyWaterValue = it[0])
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

//    init {
//        viewModelScope.launch {
//            val result = dailyWaterUseCase.getDailyWaterValue()
//            result.collect { res ->
//                _uiState.update {
//                    it.copy(dailyWaterValue = res)
//                }
//            }
//        }
//    }
//
//    fun updateCurrent() {
//        _uiState.update {
//            it.copy(currentWaterValue = it.currentWaterValue + 250)
//        }
//    }
}