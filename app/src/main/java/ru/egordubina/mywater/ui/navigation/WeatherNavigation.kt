package ru.egordubina.mywater.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.egordubina.mywater.ui.screens.home.HomeScreen
import ru.egordubina.mywater.ui.viewmodels.HomeViewModel

enum class WeatherDestinations {
    HOME
}

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherDestinations.HOME.name) {
        composable(WeatherDestinations.HOME.name) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val uiState = viewModel.uiState.collectAsState()
            HomeScreen(uiState = uiState.value, onAddGlassClick = { viewModel.updateCurrent() })
        }
    }
}