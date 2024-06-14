package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselight.R

@Composable
fun LabelBpmOnButton(modifier: Modifier=Modifier){
    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(id = R.string.bpm),
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        fontWeight = FontWeight.W700,
        color = Color.White

    )
}

@Composable
fun LabelBpmButton(bpmCount:String="65"){
    Text(
        modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
        text = bpmCount,
        textAlign = TextAlign.Center,
        fontSize = 85.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White

    )
}