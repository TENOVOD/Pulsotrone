package com.example.pulselight.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.rows.HistoryRowCart
import com.example.pulselight.ui.elements.topbars.HistoryTopBar

@Composable
fun HistoryScreen() {
    val testList = mutableListOf(
        RecordEntity(1,"80","11:07","30/01/2024"),
        RecordEntity(2,"120","11:07","30/01/2024"),
        RecordEntity(3,"80","11:07","30/01/2024"),
        RecordEntity(4,"80","11:07","30/01/2024"),
        RecordEntity(5,"80","11:07","30/01/2024"),
        RecordEntity(6,"80","11:07","30/01/2024"),
        RecordEntity(7,"80","11:07","30/01/2024"),
        RecordEntity(8,"80","11:07","30/01/2024"),
        RecordEntity(9,"80","11:07","30/01/2024"),
        RecordEntity(10,"80","11:07","30/01/2024"),
    )

        HomepageBackground{
            Column(Modifier.fillMaxSize()) {
                HistoryTopBar ()
                LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                   items(testList){ e ->
                        HistoryRowCart(e)
                    }
                    item{
                        Spacer(modifier = Modifier.height(10.dp))
                        RegularButton(onClickAction = { /*TODO*/ }, buttonText = "Очистити історію")
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                
            }

        }

}
