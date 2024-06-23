package com.example.pulselight.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pulselight.ui.backgrounds.BackgroundWithCircle
import com.example.pulselight.ui.elements.buttons.RadioButtonGroup
import com.example.pulselight.viewmodels.OnboardingViewModel

@Composable
fun OnboardingScreen(navController: NavController,viewModel:OnboardingViewModel) {
    BackgroundWithCircle {
        RadioButtonGroup(viewModel.listOnOnboardingScreen){
            navController.navigate("HomepageMeasuring")
        }

    }
}