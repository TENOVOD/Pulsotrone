package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.pulselight.R
import java.lang.reflect.Modifier

@Composable
fun TopBarLabel(textId:Int){
    Text(text = stringResource(id = textId),
        color = Color.White ,
        fontSize = 20.sp
    )
}