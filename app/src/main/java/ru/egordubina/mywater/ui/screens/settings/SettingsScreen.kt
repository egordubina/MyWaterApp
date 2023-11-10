package ru.egordubina.mywater.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.egordubina.mywater.ui.theme.MyWaterTheme

private val settingsItems = listOf(
    SettingsItem(Icons.Rounded.EmojiEvents, "Ежедневная цель", "3000 мл", null),
    SettingsItem(Icons.Rounded.WaterDrop, "Объем 1 стакана", "250 мл", null)
)

private data class SettingsItem(
    val icon: ImageVector,
    val title: String,
    val subTitle: String?,
    val action: (() -> Unit)?
)

@Composable
fun SettingsScreen() {
    LazyColumn {
        items(settingsItems, key = { it.title }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { }
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = it.icon, contentDescription = null)
                    Text(text = it.title, fontSize = 18.sp)
                }
                if (it.subTitle != null)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = it.subTitle, fontSize = 14.sp)
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                            contentDescription = null
                        )
                    }
                else
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = null
                    )
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    MyWaterTheme {
        SettingsScreen()
    }
}