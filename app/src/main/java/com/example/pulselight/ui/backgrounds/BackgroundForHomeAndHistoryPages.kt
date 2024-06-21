package com.example.pulselight.ui.backgrounds

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.theme.BgColor
import com.example.pulselight.ui.theme.ElipseBG

@Composable
fun HomepageBackground(content: @Composable () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor) // Білий фон для всього екрану
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
    ) {
        HomepageCircle()
        content()
    }
}

@Composable
fun HomepageCircle() {
    val circleSize = getScreenHeightInDp() * 1.0f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(circleSize)
            .offset(y = (-getScreenHeightInDp() / 7))
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