package com.example.pulselight.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pulselight.R
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.buttons.StartMeasuringButton
import com.example.pulselight.ui.elements.topbars.HomepageTopBar
import com.example.pulselight.viewmodels.PulseDetectorViewModel

@Composable
fun WelcomeHomepageScreen(navController: NavController) {
    val screenHeight = getScreenHeightInDp()
    HomepageBackground {
        Column(Modifier.fillMaxSize()) {
            HomepageTopBar(navController)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.homepage_start_your_first_measure),
                    modifier = Modifier.padding(20.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(screenHeight / 10))
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
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                StartMeasuringButton(){
                    navController.navigate("MeasuringPage")
                }
            }
        }

    }
}