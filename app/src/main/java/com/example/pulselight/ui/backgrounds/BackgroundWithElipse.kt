package com.example.pulselight.ui.backgrounds

import android.util.DisplayMetrics
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.theme.BgColor
import com.example.pulselight.ui.theme.ElipseBG

@Composable
fun BackgroundWithCircle(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor) // Білий фон для всього екрану
    ) {
        Circle()
        content()
    }
}
@Composable
fun Circle() {
    val circleSize = getScreenHeightInDp() * 1.0f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(circleSize)
            .offset(y = (-getScreenHeightInDp() / 8))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = ElipseBG,
                radius = circleSize.toPx() / 2,
                center = center.copy(y = circleSize.toPx() / 2)
            )
        }
    }

}

@Composable
fun getScreenHeightInDp(): Dp {
    val context = LocalContext.current
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val heightPixels = displayMetrics.heightPixels
    val density = displayMetrics.density
    return (heightPixels / density).dp
}