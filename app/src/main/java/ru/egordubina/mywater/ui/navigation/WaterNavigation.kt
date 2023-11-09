package ru.egordubina.mywater.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.egordubina.mywater.R
import ru.egordubina.mywater.ui.screens.home.HomeScreen
import ru.egordubina.mywater.ui.screens.home.HomeScreenActions
import ru.egordubina.mywater.ui.screens.settings.SettingsScreen
import ru.egordubina.mywater.ui.viewmodels.HomeViewModel

enum class WaterDestination(@StringRes val title: Int?/*, actions: List<TopAppBarAction>*/) {
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
            HomeScreen(uiState = uiState.value, onAddGlassClick = { viewModel.updateCurrent() })
        }
        composable(WaterDestination.SETTINGS.name) {
            SettingsScreen()
        }
    }
}

val screenActions: Map<WaterDestination, @Composable (NavHostController) -> Unit> = mapOf(
    // Под it подразумевается navController: NavHostController
    WaterDestination.HOME to { HomeScreenActions(it) }
)