package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pulselight.ui.fonts.customFontStyle

@Composable
fun OnboardingMainText(text:String){
    Text(
      text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = customFontStyle
    )
}