package com.example.pulselight.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pulselight.database.PulseMeasuresDatabase
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.repositories.RecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PulseDetectorViewModelFactory(val application:Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PulseDetectorViewModel::class.java)){
            return PulseDetectorViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PulseDetectorViewModel(application: Application): ViewModel() {

    val allRecords: LiveData<List<RecordEntity>>
    private val repository:RecordRepository
    private val mutableCameraState = MutableStateFlow<CameraState>(CameraState.Initial)
    var recordBpm by mutableStateOf("")

    init {
        val pulseMeasuresDatabase = PulseMeasuresDatabase.getInstance(application)
        val pulseMeasureDao = pulseMeasuresDatabase.recordDao()
        repository = RecordRepository(pulseMeasureDao)
        allRecords=repository.recordList
        startCamera()
    }

//    fun getRecordById(recordId: Int, callback: (RecordEntity?) -> Unit) {
//        viewModelScope.launch {
//            val record = repository.getRecordById(recordId)
//            callback(record)
//        }
//    }
    fun changeBpm(newBpm:String){
        recordBpm=newBpm
    }
    fun addRecord(callback: (Long) -> Unit) {
        val record = RecordEntity(recordBpm)
        viewModelScope.launch {
            val recordId = repository.addRecord(record)
            callback(recordId)
        }
    }
   fun startCamera() {
        viewModelScope.launch {
            mutableCameraState.value = CameraState.Active
        }
    }

     fun stopCamera() {
        viewModelScope.launch {
            mutableCameraState.value = CameraState.Inactive
        }
    }

}


sealed class CameraState {
    object Initial : CameraState()
    object Active : CameraState()
    object Inactive : CameraState()
}