package com.example.pulselight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.pulselight.ui.elements.buttons.OnBoardingRadioButton
import com.example.pulselight.ui.screens.LoadingScreen
import com.example.pulselight.ui.screens.OnboardingScreen
import com.example.pulselight.ui.screens.WelcomeHomepageScreen
import com.example.pulselight.ui.theme.PulseLightTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PulseLightTheme {
                WelcomeHomepageScreen()

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PulseLightTheme {
        WelcomeHomepageScreen()
    }
}