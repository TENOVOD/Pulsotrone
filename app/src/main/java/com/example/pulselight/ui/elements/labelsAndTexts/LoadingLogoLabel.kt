package com.example.pulselight.ui.elements.labelsAndTexts


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pulselight.R
import com.example.pulselight.ui.fonts.customFontStyle

@Composable
fun LoadingLogoLabel(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        fontSize = 50.sp,
        fontWeight = FontWeight.W900,
        fontFamily = customFontStyle
    )
}