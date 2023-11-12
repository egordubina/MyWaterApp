package ru.egordubina.mywater.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.egordubina.mywater.ui.theme.MyWaterTheme
import ru.egordubina.mywater.ui.uistates.settings.SettingsItemUiState
import ru.egordubina.mywater.ui.uistates.settings.SettingsUiState

@Composable
fun SettingsScreen(uiState: SettingsUiState, changeGlassVolume: (Int) -> Unit) {
    var dialogGlassVolumeIsVisible by remember { mutableStateOf(false) }
    // fixme: повысить производительность за счет key
    // todo: попробоовать вынести modifier clickable в data class и добавлять его при отрисовке в lazy column
    val settingsItems: List<SettingsItemUiState> = listOf(
        SettingsItemUiState.Title(
            text = "Общие",
        ),
        SettingsItemUiState.DefaultSettingsItemUiState(
            icon = Icons.Rounded.EmojiEvents,
            title = "Ежедневная цель",
            subTitle = "${uiState.dailyWaterValue} мл",
            action = {}
        ),
        SettingsItemUiState.DefaultSettingsItemUiState(
            icon = Icons.Rounded.WaterDrop,
            title = "Объем стакана",
            subTitle = "${uiState.glassVolume} мл",
            action = { dialogGlassVolumeIsVisible = true }
        ),
    )
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        items(settingsItems) { it ->
            when (it) {
                is SettingsItemUiState.Title -> Text(
                    text = it.text,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                is SettingsItemUiState.DefaultSettingsItemUiState -> {
                    SettingsItem(
                        icon = it.icon,
                        title = it.title,
                        subTitle = it.subTitle,
                        action = it.action
                    )
                }

                is SettingsItemUiState.Slider -> {}
            }
        }
    }
    if (dialogGlassVolumeIsVisible) {
        var textValue by remember { mutableIntStateOf(uiState.glassVolume) }
        AlertDialog(
            onDismissRequest = { dialogGlassVolumeIsVisible = false },
            title = { Text(text = "Изменить объём стакана") },
            text = {
                OutlinedTextField(
                    value = textValue.toString(),
                    onValueChange = {
                        textValue = try {
                            it.toInt()
                        } catch (_: Exception) {
                            0
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        dialogGlassVolumeIsVisible = false
                        changeGlassVolume(textValue)
                    })
                )
            },
            dismissButton = {
                TextButton(onClick = { dialogGlassVolumeIsVisible = false }) {
                    Text(text = "Отмена")
                }
            },
            confirmButton = {
                Button(onClick = {
                    dialogGlassVolumeIsVisible = false
                    changeGlassVolume(textValue)
                }) {
                    Text(text = "Изменить")
                }
            }
        )
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subTitle: String?,
    action: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { action() }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(text = title, fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
        }
        if (subTitle != null)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = subTitle,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        else
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                contentDescription = null
            )
    }
}

@PreviewLightDark
@Composable
fun SettingsItemPreview() {
    MyWaterTheme {
        SettingsItem(
            icon = Icons.Rounded.EmojiEvents,
            title = "Еженедельная цель",
            subTitle = "3000 мл",
            action = {}
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    MyWaterTheme {
        SettingsScreen(uiState = SettingsUiState(), changeGlassVolume = {})
    }
}