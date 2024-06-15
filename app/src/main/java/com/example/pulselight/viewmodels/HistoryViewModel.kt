package com.example.pulselight.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pulselight.database.PulseMeasuresDatabase
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.repositories.RecordRepository

class HistoryViewModelFactory(val application: Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            return HistoryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HistoryViewModel(application: Application): ViewModel() {
    val allRecords: LiveData<List<RecordEntity>>
    private val repository: RecordRepository

    init {
        val pulseMeasuresDatabase = PulseMeasuresDatabase.getInstance(application)
        val pulseMeasureDao = pulseMeasuresDatabase.recordDao()
        repository = RecordRepository(pulseMeasureDao)
        allRecords = repository.recordList

    }
    fun deleteAllRecords(){
        repository.deleteAllRecords()
    }

}