package com.example.pulselight.ui.elements.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import com.example.pulselight.ui.elements.labelsAndTexts.TopBarLabel
import com.example.pulselight.ui.theme.ButtonColor

@Composable
fun ResultTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ButtonColor)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopBarLabel(R.string.result, Modifier.padding(start = 20.dp))
        Box(Modifier.padding(end = 10.dp)) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopBarLabel(R.string.history)
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        modifier = Modifier.padding(end = 5.dp, start = 5.dp),
                        painter = painterResource(id = R.drawable.history_icon),
                        contentDescription = "history icon"
                    )
                }
            }

        }
    }
}