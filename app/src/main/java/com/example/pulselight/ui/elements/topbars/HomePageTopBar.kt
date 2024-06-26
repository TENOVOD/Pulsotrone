package com.example.pulselight.ui.elements.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pulselight.ui.elements.labelsAndTexts.TopBarLabel
import com.example.pulselight.ui.theme.ButtonColor


@Composable
fun HomepageTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ButtonColor)
            .height(60.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.fillMaxHeight()) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .clickable {
                        navController.navigate("HistoryScreen")
                    }, verticalAlignment = Alignment.CenterVertically) {
                TopBarLabel(R.string.history)
                IconButton(onClick = { navController.navigate("HistoryScreen") }) {
                    Image(
                        modifier = Modifier.padding(end = 5.dp, start = 5.dp),
                        painter = painterResource(id = R.drawable.history_icon),
                        contentDescription = "HistoryScreen"
                    )
                }
            }
        }

    }
}