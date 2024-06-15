package com.example.pulselight.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pulselight.database.PulseMeasuresDatabase
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.repositories.RecordRepository
import kotlinx.coroutines.coroutineScope


class ResultViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ResultViewModel(application: Application) : ViewModel() {
    val allRecords: LiveData<List<RecordEntity>>
    private val repository: RecordRepository
    var recordBpm by mutableStateOf(0)
    var recordTime by mutableStateOf("")
    var recordDate by mutableStateOf("")

    init {
        val pulseMeasuresDatabase = PulseMeasuresDatabase.getInstance(application)
        val pulseMeasureDao = pulseMeasuresDatabase.recordDao()
        repository = RecordRepository(pulseMeasureDao)
        allRecords = repository.recordList
    }

    fun getRecordById(id: Long, list: List<RecordEntity>) {
         list.forEach{
             
         }

        val result = list.find {
            it.id == id
        }
        Log.d("RESULT",result.toString())
        if (result != null) {
            recordBpm =  result.bpm.toInt()
            recordTime = result.time
            recordDate = result.date
        }
        Log.d("RESULTAF",recordBpm.toString())
    }
}