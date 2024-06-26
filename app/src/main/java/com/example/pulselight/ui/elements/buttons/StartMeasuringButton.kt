package com.example.pulselight.ui.elements.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp

@Composable
fun StartMeasuringButton(starMeasuring:()->Unit) {
    val screenHeight = getScreenHeightInDp();
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(screenHeight/5),
        horizontalArrangement = Arrangement.Center
    ) {

        IconButton(onClick = starMeasuring,
            modifier =Modifier.padding(10.dp).fillMaxSize() ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.start_measuring_button),
                contentDescription = "start_measuring_button"
            )
        }
    }

}