package com.example.pulselight.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pulselight.R

@Composable
fun LoadingLogoLabel(){
    Text(text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        fontWeight = FontWeight.W800
    )
}