package com.example.pulselight.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.example.pulselight.ui.theme.StatusBlue
import com.example.pulselight.ui.theme.StatusGreen
import com.example.pulselight.ui.theme.StatusRed

@Composable
fun ResultProgressBar(pulse:Int) {
    val localPulseValue = if(pulse>120) 120 else pulse
    var boxWidth by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
            .onSizeChanged {
                boxWidth = it.width
            },


    ) {
        Canvas(modifier = Modifier.fillMaxWidth()
            .height(20.dp)
            .clip(RoundedCornerShape(12.dp))) {
            val width = size.width
            val height = size.height


            drawRoundRect(
                color = StatusBlue,
                topLeft = Offset(0f, 0f),
                size = Size(width * 0.5f, height),
            )

            drawRoundRect(
                color = StatusGreen,
                topLeft = Offset(width * 0.5f, 0f),
                size = Size(width * 0.8f, height),
            )

            drawRoundRect(
                color = StatusRed,
                topLeft = Offset(width * 0.8f, 0f),
                size = Size(width * 0.33f, height),
            )
        }


        val sliderPosition = with(density) { (localPulseValue.coerceIn(0, 120) / 120f) * (boxWidth - 20.dp.toPx()) }
        Box(
            modifier = Modifier
                .offset { IntOffset(sliderPosition.toInt(), 0) }
                .height(35.dp)
                .width(10.dp)
                .offset(y= (-5).dp)
                .clip(RoundedCornerShape(3.dp))
                .border(2.dp, Color.LightGray)
                .background(Color.White,)

        )
    }

}