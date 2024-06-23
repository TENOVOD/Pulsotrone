package com.example.pulselight.ui.elements.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.labelsAndTexts.OnboardingLabel
import com.example.pulselight.ui.elements.labelsAndTexts.OnboardingMainText

@Composable
fun OnboardingForm(labelText: String, mainText: String, image: Int) {
    val dpHeight = getScreenHeightInDp()

    Column(
        modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(id = image),
                contentDescription = "Onboarding image"
            )
        }
        Spacer(modifier = Modifier.height(dpHeight/12))
        OnboardingLabel(labelText = labelText)
        Spacer(modifier = Modifier.height(dpHeight/27))
        OnboardingMainText(text = mainText)
    }
}

