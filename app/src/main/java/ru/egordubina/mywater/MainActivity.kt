package ru.egordubina.mywater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.egordubina.mywater.ui.navigation.WeatherNavigation
import ru.egordubina.mywater.ui.theme.MyWaterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWaterTheme {
                WeatherNavigation()
            }
        }
    }
}
