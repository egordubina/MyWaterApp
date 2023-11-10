package ru.egordubina.mywater.ui.uistates

data class SettingsUiState(
    val dailyWaterValue: Int = 0,
    var glassVolume: Int = 250
)