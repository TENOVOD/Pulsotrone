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


@Composable
fun DetectionFingerLabel(textId: Int) {
    Text(
        text = stringResource(id = textId),
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.W800,
        textAlign = TextAlign.Center
    )
}