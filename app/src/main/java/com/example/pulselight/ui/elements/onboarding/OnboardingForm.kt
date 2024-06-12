package com.example.pulselight.ui.elements.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.labelsAndTexts.OnboardingLabel
import com.example.pulselight.ui.elements.labelsAndTexts.OnboardingMainText

@Composable
fun OnboardingForm(labelText: String, mainText: String, image: Int) {
    val dpHeight = getScreenHeightInDp()

    val aaa = { Log.d("aa","aa")}
    Column(
        modifier = Modifier.fillMaxWidth(),
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
        Spacer(modifier = Modifier.height(dpHeight/20))
        RegularButton(onClickAction = {Log.d("aa","aa")}," Почати!")
    }
}

