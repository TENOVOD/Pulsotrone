package com.example.pulselight.ui.elements.rows

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.ui.fonts.customFontStyle
import com.example.pulselight.ui.theme.ButtonColor


@Composable
fun HistoryRowCart(record: RecordEntity, goToDetailsById: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .fillMaxWidth()
            .clickable { goToDetailsById() }
    ) {
        HistoryBpmAndDateRow(record.bpm, record.time, record.date)
    }

}


@Composable
fun HistoryBpmAndDateRow(bpmText: String, time: String, date: String) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically
    ) {
        BigBpmText(bpbValue = bpmText, modifier = Modifier.fillMaxWidth(0.5f))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                        .width(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(ButtonColor)
                )

                TimeAndDate(time, date)
            }
        }
    }
}

@Composable
fun BigBpmText(bpbValue: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$bpbValue BPM",
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.W500,
            fontFamily = customFontStyle
        )
    }

}

@Composable
fun TimeAndDate(time: String, date: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            Text(
                modifier = Modifier.padding(3.dp),
                text = time,
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                fontFamily = customFontStyle

            )
            Text(
                modifier = Modifier.padding(3.dp),
                text = date,
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontFamily = customFontStyle
            )
        }
    }
}