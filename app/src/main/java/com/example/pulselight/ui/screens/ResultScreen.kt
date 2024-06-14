package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.elements.ResultBlock
import com.example.pulselight.ui.elements.ResultProgressBar
import com.example.pulselight.ui.elements.labelsAndTexts.LegendStatus
import com.example.pulselight.ui.elements.labelsAndTexts.LegendWithBpmLimits
import com.example.pulselight.ui.theme.StatusBlue
import com.example.pulselight.ui.theme.StatusGreen
import com.example.pulselight.ui.theme.StatusRed


@Composable
fun ResultScreen() {
    HomepageBackground{
        ResultBlock{
            Column {
                Spacer(modifier = Modifier.height(30.dp))
                ResultProgressBar(50)
                Spacer(modifier = Modifier.height(20.dp))
                LegendWithBpmLimits(color = StatusBlue, textStatus = R.string.slowed_down, textBpmLimit = R.string.bpm_60)
                LegendWithBpmLimits(color = StatusGreen, textStatus = R.string.regular, textBpmLimit = R.string.bpm_60_100)
                LegendWithBpmLimits(color = StatusRed, textStatus = R.string.accelerated, textBpmLimit = R.string.bpm_100)

            }
        }
    }


}