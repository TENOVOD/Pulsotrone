package com.example.pulselight.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.example.pulselight.ui.theme.StatusBlue
import com.example.pulselight.ui.theme.StatusGreen
import com.example.pulselight.ui.theme.StatusRed
import kotlin.math.max
import kotlin.math.min
@Composable
fun ResultProgressBar(pulse:Int) {
    var boxWidth by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
            .onSizeChanged {
                boxWidth = -it.width/2
            },
        contentAlignment = Alignment.Center,

    ) {
        Canvas(modifier = Modifier.fillMaxWidth()
            .height(20.dp)
            .clip(RoundedCornerShape(12.dp))) {
            val width = size.width
            val height = size.height


            drawRoundRect(
                color = StatusBlue,
                topLeft = Offset(0f, 0f),
                size = Size(width * 0.33f, height),
            )

            drawRoundRect(
                color = StatusGreen,
                topLeft = Offset(width * 0.33f, 0f),
                size = Size(width * 0.34f, height),
            )

            drawRoundRect(
                color = StatusRed,
                topLeft = Offset(width * 0.67f, 0f),
                size = Size(width * 0.33f, height),
            )
        }


        val sliderPosition = with(density) { (pulse.coerceIn(0, 150) / 150f) * (boxWidth - 20.dp.toPx()) }
        Box(
            modifier = Modifier
                .offset { IntOffset(sliderPosition.toInt(), 0) }
                .height(35.dp)
                .width(10.dp)
                .clip(RoundedCornerShape(3.dp))
                .border(2.dp, Color.LightGray)
                .background(Color.White,)



        )
    }

}