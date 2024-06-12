package com.example.pulselight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pulselight.ui.screens.LoadingScreen
import com.example.pulselight.ui.screens.OnboardingScreen
import com.example.pulselight.ui.theme.PulseLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PulseLightTheme {
                OnboardingScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PulseLightTheme {
        OnboardingScreen()
    }
}