package ru.egordubina.mywater.ui.uistates.home

data class HomeUiState(
    val dailyWaterValue: Int = 0,
    var currentWaterValue: Int = 0,
    var glassVolume: Int = 250,
)