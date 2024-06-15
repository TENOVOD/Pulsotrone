package com.example.pulselight

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pulselight.navigations.AppNavigation
import com.example.pulselight.ui.elements.ResultBlock
import com.example.pulselight.ui.elements.buttons.OnBoardingRadioButton
import com.example.pulselight.ui.screens.HeartMeasuringScreen
import com.example.pulselight.ui.screens.HistoryScreen
import com.example.pulselight.ui.screens.LoadingScreen
import com.example.pulselight.ui.screens.OnboardingScreen
import com.example.pulselight.ui.screens.ResultScreen
import com.example.pulselight.ui.screens.WelcomeHomepageScreen
import com.example.pulselight.ui.screens.camera_content.CameraPreview
import com.example.pulselight.ui.theme.PulseLightTheme
import com.example.pulselight.viewmodels.PulseDetectorViewModel
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PulseLightTheme {
                AppNavigation()
            }
        }
    }
}





@Composable
fun CameraScreen(onChangeFinalResult:(Int)->Unit,onFingerDetected:(Boolean)->Unit,onPulseDetected: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Black)
    ) {
        CameraPreview(onChangeFinalResult,onFingerDetected,onPulseDetected)
    }
}