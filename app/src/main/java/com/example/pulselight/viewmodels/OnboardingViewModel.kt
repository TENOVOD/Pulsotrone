package com.example.pulselight.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pulselight.R
import com.example.pulselight.models.OnboardingEntity

class OnboardingViewModel:ViewModel() {

    val listOnOnboardingScreen = listOf(
        OnboardingEntity(
            R.string.onboarding1_label_text,
            R.string.onboarding1_main_text,
            R.drawable.onboarding1),
        OnboardingEntity(
            R.string.onboarding2_label_text,
            R.string.onboarding2_main_text,
            R.drawable.onboarding2),
        OnboardingEntity(
            R.string.onboarding3_label_text,
            R.string.onboarding3_main_text,
            R.drawable.onboarding3),
    )

}