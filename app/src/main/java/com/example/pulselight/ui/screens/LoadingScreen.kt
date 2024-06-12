package com.example.pulselight.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.BackgroundWithCircle
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.LinearProgressTool
import com.example.pulselight.ui.elements.labelsAndTexts.LoadingLogoLabel


@Composable
fun LoadingScreen() {
    val dpHeight = getScreenHeightInDp()
    BackgroundWithCircle {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dpHeight / 5),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.heart_with_glare),
                    contentDescription = "Heart"
                )
                Image(
                    painter = painterResource(id = R.drawable.pulse_wave),
                    contentDescription = "Pulse wave on heart"
                )
            }
            LoadingLogoLabel()
            Spacer(modifier = Modifier.height(dpHeight/3.25f))
            LinearProgressTool()
        }


    }


}