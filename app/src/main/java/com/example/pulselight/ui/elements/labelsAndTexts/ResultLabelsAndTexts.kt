package com.example.pulselight.ui.elements.labelsAndTexts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselight.R
import com.example.pulselight.ui.theme.ElipseBG
import com.example.pulselight.ui.theme.StatusBlue


@Composable
fun LegendWithBpmLimits(textStatus: Int, textBpmLimit: Int, color: Color) {
    Column(
        Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LegendStatus(textStatus, color)
            TextBpmLimit(textBpmLimit)
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun TimeAndDateWithIcon(time: String, date: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.clock_icon),
            contentDescription = "creating time"
        )
        Column {
            Text(
                modifier = Modifier.padding(3.dp),
                text = time,
                color = Color.DarkGray,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.padding(3.dp),
                text = date,
                color = Color.DarkGray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun LegendStatus(textId: Int, color: Color) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(ElipseBG)
            .padding(5.dp)
    ) {
        Row(
            //modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .height(10.dp)
                    .width(10.dp)
                    .background(color)
            )
            StatusText(textId = textId)
        }
    }

}

@Composable
fun TextBpmLimit(textId: Int) {
    Text(
        text = stringResource(id = textId),
        color = Color.Black,
        fontSize = 14.sp

    )
}

@Composable
fun ResultCartLabel(textId: Int) {
    Text(
        text = stringResource(id = textId),
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.W800
    )
}

@Composable
fun ResultText(textId: Int, color: Color) {
    Text(
        text = stringResource(id = textId),
        color = color,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun StatusText(textId: Int) {
    Text(
        text = stringResource(id = textId),
        color = Color.Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}

