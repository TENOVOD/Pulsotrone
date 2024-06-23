package com.example.pulselight.ui.elements


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.fonts.customFontStyle
import com.example.pulselight.ui.theme.LinearProgressEmpty
import com.example.pulselight.ui.theme.LinearProgressFull


@Composable
fun LinearProgressTool(animationDuration: Int) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        val targetProgress = 1f
        val animationSpec = tween<Float>(
            durationMillis = animationDuration,
            easing = LinearEasing
        )

        animate(
            initialValue = 0f,
            targetValue = targetProgress,
            animationSpec = animationSpec
        ) { value, _ ->
            progress = value
        }
    }
    Box {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp)),

            progress = progress,
            color = LinearProgressFull,
            trackColor = LinearProgressEmpty,
            strokeCap = StrokeCap.Round
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(5.dp)),
            text = (progress * 100).toInt().toString().plus("%"),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = customFontStyle

        )
    }


}