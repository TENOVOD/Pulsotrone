package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pulselight.R

import com.example.pulselight.models.OnboardingEntity
import com.example.pulselight.ui.backgrounds.BackgroundWithCircle
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.buttons.RadioButtonGroup
import com.example.pulselight.ui.elements.onboarding.OnboardingForm

@Composable
fun OnboardingScreen() {

    val listOnOnboardingScreen = listOf(
        OnboardingEntity(R.string.onboarding1_label_text,R.string.onboarding1_main_text,R.drawable.onboarding1),
        OnboardingEntity(R.string.onboarding2_label_text,R.string.onboarding2_main_text,R.drawable.onboarding2),
        OnboardingEntity(R.string.onboarding3_label_text,R.string.onboarding3_main_text,R.drawable.onboarding3),
    )

    RadioButtonGroup(listOnOnboardingScreen)


}