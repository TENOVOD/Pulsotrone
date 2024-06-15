package com.example.pulselight.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.rows.HistoryRowCart
import com.example.pulselight.ui.elements.topbars.HistoryTopBar
import com.example.pulselight.viewmodels.HistoryViewModel

@Composable
fun HistoryScreen(navController: NavController, vm: HistoryViewModel) {
    val resultList by vm.allRecords.observeAsState(listOf())
    val clearRecords = clearList(resultList)
    val isEmptyList = clearRecords.isEmpty()
    HomepageBackground {
        Column(Modifier.fillMaxSize()) {
            HistoryTopBar(navController)
            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(clearRecords) { e ->
                    HistoryRowCart(e){
                        navController.navigate("ResultScreen/${e.id}")
                    }
                }
                if(!isEmptyList){
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        RegularButton(
                            onClickAction = { vm.deleteAllRecords() },
                            buttonText = "Очистити історію"
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

            }


        }

    }

}
//тимчасове рішення
fun clearList(list:List<RecordEntity>):List<RecordEntity>{
    var mutableList = mutableListOf<RecordEntity>()
    mutableList.clear()
    var time = ""
    list.forEach {el->
        if(time==""){
            time=el.time
            mutableList.add(el)
        }else if(time!=el.time){
            time=el.time
            mutableList.add(el)
        }
    }
    return  mutableList
}
