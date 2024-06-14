package com.example.pulselight.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ResultBlock(content: @Composable () -> Unit) {
    Box(modifier = Modifier
        .padding(20.dp)
        .clip(RoundedCornerShape(16.dp))
        .fillMaxWidth()
        .fillMaxHeight(0.4f)
        .background(com.example.pulselight.ui.theme.ResultBlock)
    ){
        content()
    }
}