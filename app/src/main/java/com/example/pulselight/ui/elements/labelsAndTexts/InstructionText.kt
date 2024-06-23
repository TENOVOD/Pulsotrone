package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pulselight.ui.fonts.customFontStyle

@Composable
fun InstructionText (textId:Int){
    Text(
        text = stringResource(id = textId),
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center,
        fontFamily = customFontStyle
    )
}