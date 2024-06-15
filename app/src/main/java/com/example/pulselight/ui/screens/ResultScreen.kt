package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.elements.ResultBlock
import com.example.pulselight.ui.elements.ResultProgressBar
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.labelsAndTexts.LegendStatus
import com.example.pulselight.ui.elements.labelsAndTexts.LegendWithBpmLimits
import com.example.pulselight.ui.elements.labelsAndTexts.ResultCartLabel
import com.example.pulselight.ui.elements.labelsAndTexts.ResultText
import com.example.pulselight.ui.elements.labelsAndTexts.TimeAndDateWithIcon
import com.example.pulselight.ui.elements.topbars.ResultTopBar
import com.example.pulselight.ui.theme.StatusBlue
import com.example.pulselight.ui.theme.StatusGreen
import com.example.pulselight.ui.theme.StatusRed


@Composable
fun ResultScreen() {
    HomepageBackground{
        ResultTopBar()
        Column(Modifier.fillMaxSize()) {
            ResultBlock{
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            ResultCartLabel(textId = R.string.your_result)
                            ResultText(textId = R.string.regular, color = StatusGreen )
                        }
                        TimeAndDateWithIcon("11:07", "30/01/2024")
                    }
                    ResultProgressBar(70)
                    Spacer(modifier = Modifier.height(20.dp))
                    LegendWithBpmLimits(color = StatusBlue, textStatus = R.string.slowed_down, textBpmLimit = R.string.bpm_60)
                    LegendWithBpmLimits(color = StatusGreen, textStatus = R.string.regular, textBpmLimit = R.string.bpm_60_100)
                    LegendWithBpmLimits(color = StatusRed, textStatus = R.string.accelerated, textBpmLimit = R.string.bpm_100)

                }

            }
            Column(Modifier.fillMaxSize().padding(bottom = 20.dp), verticalArrangement = Arrangement.Bottom,horizontalAlignment = Alignment.CenterHorizontally) {
                RegularButton(onClickAction = { /*TODO*/ }, buttonText = stringResource(id = R.string.ready))
            }
        }

    }


}