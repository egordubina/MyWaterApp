package ru.egordubina.mywater.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.egordubina.mywater.R
import ru.egordubina.mywater.ui.screens.home.HomeScreen
import ru.egordubina.mywater.ui.screens.home.HomeScreenActions
import ru.egordubina.mywater.ui.screens.settings.SettingsScreen
import ru.egordubina.mywater.ui.viewmodels.HomeViewModel
import ru.egordubina.mywater.ui.viewmodels.SettingsViewModel

enum class WaterDestination(@StringRes val title: Int?) {
    HOME(title = null),
    SETTINGS(title = R.string.label__settings)
}

@Composable
fun WaterNavigation(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = WaterDestination.HOME.name,
        modifier = modifier
    ) {
        composable(WaterDestination.HOME.name) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val uiState = viewModel.uiState.collectAsState()
            HomeScreen(
                uiState = uiState.value,
                onAddGlassClick = { viewModel.updateCurrentData(data = uiState.value.currentWaterValue + uiState.value.glassVolume) }
            )
        }
        composable(WaterDestination.SETTINGS.name) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            val uiState = viewModel.uiState.collectAsState()
            SettingsScreen(uiState = uiState.value, changeGlassVolume = { viewModel.changeGlassVolume(it) })
        }
    }
}

/**
 *  Дополнтельные действия (actions row) для каждого экрана
 */
val screenActions: Map<WaterDestination, @Composable (NavHostController) -> Unit> = mapOf(
    // Под it подразумевается navController: NavHostController
    WaterDestination.HOME to { HomeScreenActions(navController = it) }
)