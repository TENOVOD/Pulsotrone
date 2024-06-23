package com.example.pulselight.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pulselight.R
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.rows.HistoryRowCart
import com.example.pulselight.ui.elements.topbars.HistoryTopBar
import com.example.pulselight.ui.theme.ButtonColor
import com.example.pulselight.ui.theme.TrackColor
import com.example.pulselight.viewmodels.HistoryViewModel

@Composable
fun HistoryScreen(navController: NavController, vm: HistoryViewModel) {
    val resultList by vm.allRecords.observeAsState(listOf())
    val clearRecords = clearList(resultList)
    val isEmptyList = clearRecords.isEmpty()
    val stateVertical = rememberLazyListState()

    HomepageBackground {
        Column(Modifier.fillMaxSize()) {
            HistoryTopBar(navController)
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = stateVertical,
                    modifier = Modifier
                        .fillMaxWidth()
                        .simpleVerticalScrollbar(stateVertical),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(clearRecords) { e ->
                        HistoryRowCart(e) {
                            navController.navigate("ResultScreen/${e.id}")
                        }
                    }
                    if (!isEmptyList) {
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                            RegularButton(
                                onClickAction = { vm.deleteAllRecords() },
                                buttonText = stringResource(id = R.string.clean_story)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                }
            }


        }

    }

}


@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp
): Modifier {

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 3000)
    )
    return this
        .padding(top = 10.dp, start = 5.dp, end = 5.dp, bottom = 20.dp)
        .clip(RoundedCornerShape(5.dp))
        .drawWithContent {
            drawContent()

            val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
            val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

            drawRoundRect(
                color = TrackColor,
                topLeft = Offset(this.size.width - width.toPx(), 0f),
                size = Size(width.toPx(), this.size.height),
                cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx()),
            )

            if (needDrawScrollbar && firstVisibleElementIndex != null) {
                val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
                val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
                val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

                drawRoundRect(
                    color = ButtonColor,
                    topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                    size = Size(width.toPx(), scrollbarHeight),
                    alpha = alpha,
                    cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                )
            }
        }
}


//тимчасове рішення
fun clearList(list: List<RecordEntity>): List<RecordEntity> {
    var mutableList = mutableListOf<RecordEntity>()
    mutableList.clear()
    var time = ""
    list.forEach { el ->
        if (time == "") {
            time = el.time
            mutableList.add(el)
        } else if (time != el.time) {
            time = el.time
            mutableList.add(el)
        }
    }
    return mutableList
}
