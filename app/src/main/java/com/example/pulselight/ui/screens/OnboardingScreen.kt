package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.pulselight.R

import com.example.pulselight.models.OnboardingEntity
import com.example.pulselight.ui.backgrounds.BackgroundWithCircle
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.buttons.RadioButtonGroup
import com.example.pulselight.ui.elements.onboarding.OnboardingForm
import com.example.pulselight.viewmodels.OnboardingViewModel

@Composable
fun OnboardingScreen(navController: NavController,viewModel:OnboardingViewModel) {
    BackgroundWithCircle {

        RadioButtonGroup(viewModel.listOnOnboardingScreen){
            navController.navigate("HomepageMeasuring")
        }

    }
}