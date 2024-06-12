package com.example.pulselight.ui.elements


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.theme.LinearProgressEmpty
import com.example.pulselight.ui.theme.LinearProgressFull


@Composable
fun LinearProgressTool() {
    Box{
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp)),

            progress = 0.70f,
            color = LinearProgressFull,
            trackColor = LinearProgressEmpty,
            strokeCap = StrokeCap.Round
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(5.dp))
               // .offset(y= -(20).dp)
            ,
            text = "70%",
            color = Color.White,
            textAlign = TextAlign.Center,

            )
    }


}