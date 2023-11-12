package ru.egordubina.mywater.ui.uistates.settings

import androidx.compose.ui.graphics.vector.ImageVector

sealed class SettingsItemUiState {
    data class DefaultSettingsItemUiState(
        val icon: ImageVector,
        val title: String,
        val subTitle: String?,
        val action: () -> Unit
    ) : SettingsItemUiState()

    data class Title(val text: String) : SettingsItemUiState()
    data class Slider(
        val title: String,
        val value: Float,
        val action: (Float) -> Unit
    ) : SettingsItemUiState()
}