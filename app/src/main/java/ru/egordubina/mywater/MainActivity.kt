package ru.egordubina.mywater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.egordubina.mywater.ui.navigation.WaterDestination
import ru.egordubina.mywater.ui.navigation.WaterNavigation
import ru.egordubina.mywater.ui.navigation.screenActions
import ru.egordubina.mywater.ui.theme.MyWaterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = WaterDestination.valueOf(
                backStackEntry?.destination?.route ?: WaterDestination.HOME.name
            )
            val canNavigateBack = navController.previousBackStackEntry != null
            MyWaterTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                AnimatedContent(targetState = currentScreen.title, label = "") {
                                    Text(text = if (it != null) stringResource(id = it) else "")
                                }
                            },
                            navigationIcon = {
                                AnimatedVisibility(
                                    visible = canNavigateBack,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Rounded.ArrowBackIosNew,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            actions = {
                                AnimatedVisibility(
                                    visible = currentScreen in screenActions,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    screenActions[currentScreen]?.invoke(navController)
                                }
                            }
                        )
                    }
                ) {
                    WaterNavigation(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    )
                }
            }
        }
    }
}
