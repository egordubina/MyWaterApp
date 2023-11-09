package ru.egordubina.mywater.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.egordubina.mywater.ui.navigation.WaterDestination
import ru.egordubina.mywater.ui.theme.MyWaterTheme
import ru.egordubina.mywater.ui.uistates.HomeUiState

@Composable
fun HomeScreen(uiState: HomeUiState, onAddGlassClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        DailyProgress(dayValue = uiState.dailyWaterValue, currentValue = uiState.currentWaterValue)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Цель",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                // Todo: функционал для выбора количества воды в сутки
                Text(
                    text = "3000 Мл",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Сегодня",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                // Todo: функционал для выбора количества воды в стакане
                val curWaterValue by animateIntAsState(
                    targetValue = uiState.currentWaterValue,
                    animationSpec = tween(750, easing = FastOutSlowInEasing),
                    label = ""
                )
                Text(
                    text = "$curWaterValue Мл",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
        Button(
            onClick = onAddGlassClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Добавить стакан (250 мл)")
        }
    }
}

// Todo: функционал для показа статистики выпитой воды
@Composable
fun DailyProgress(dayValue: Int, currentValue: Int) {
    var height by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val progress by animateFloatAsState(
        targetValue = currentValue.toFloat() / dayValue.toFloat(),
        animationSpec = tween(750, easing = FastOutSlowInEasing),
        label = ""
    )
    val curValue by animateIntAsState(
        targetValue = (currentValue.toFloat() / dayValue.toFloat() * 100).toInt(),
        animationSpec = tween(750, easing = FastOutSlowInEasing),
        label = ""
    )
    val color by animateColorAsState(
        targetValue = if (curValue >= 100) Color.Green else MaterialTheme.colorScheme.onPrimaryContainer,
        animationSpec = tween(750, easing = FastOutSlowInEasing),
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (curValue >= 100) Color.Green else MaterialTheme.colorScheme.onBackground,
        animationSpec = tween(750, easing = FastOutSlowInEasing),
        label = ""
    )
    val helpText = when (curValue) {
        in 0..25 -> "Пей больше воды"
        in 26..50 -> "Так держать! Продолжай пить воду"
        in 51..75 -> "Почти успех! Больше воды!"
        in 76..100 -> "Практически дневная норма! Осталось чуть-чуть"
        else -> "Пей больше воды"
    }
    Column {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = progress,
                strokeWidth = 24.dp,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                strokeCap = StrokeCap.Round,
                color = color,
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned { with(density) { height = it.size.width.toDp() } }
                    .height(height)
            )
            Text(
                text = "$curValue%",
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        AnimatedVisibility(visible = curValue < 100) {
            Column {
                AnimatedContent(targetState = helpText, label = "") {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "Ещё ${(dayValue - currentValue) / 250} стаканов",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyWaterTheme {
        HomeScreen(
            uiState = HomeUiState(
                dailyWaterValue = 3000,
                currentWaterValue = 1500
            )
        ) {}
    }
}

@Composable
fun HomeScreenActions(navController: NavHostController) {
    Row {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
        }
        IconButton(onClick = { navController.navigate(WaterDestination.SETTINGS.name) }) {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
        }
    }
}