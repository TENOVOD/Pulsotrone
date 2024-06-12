package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.BackgroundWithCircle
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.onboarding.OnboardingForm

@Composable
fun OnboardingScreen() {
    val dpHeight = getScreenHeightInDp()
    BackgroundWithCircle {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dpHeight / 7),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingForm(
                labelText = stringResource(id = R.string.onboarding1_label_text),
                mainText = stringResource(id = R.string.onboarding2_main_text),
                image = R.drawable.onboarding1
            )
        }
    }
}