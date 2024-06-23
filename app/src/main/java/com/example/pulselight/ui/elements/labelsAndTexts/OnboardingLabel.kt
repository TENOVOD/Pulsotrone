package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pulselight.ui.fonts.customFontStyle

@Composable
fun OnboardingLabel(labelText: String){
    Text(
       modifier = Modifier.fillMaxWidth(),
        text = labelText,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W700,
        fontSize = 22.sp,
        fontFamily = customFontStyle
    )
}