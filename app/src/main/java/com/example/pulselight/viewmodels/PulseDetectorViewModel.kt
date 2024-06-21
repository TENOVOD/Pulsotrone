package com.example.pulselight.viewmodels

import android.app.Application
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pulselight.database.PulseMeasuresDatabase
import com.example.pulselight.models.RecordEntity
import com.example.pulselight.pulse_analyzer.PulseAnalyzer
import com.example.pulselight.repositories.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class PulseDetectorViewModelFactory(val application:Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PulseDetectorViewModel::class.java)){
            return PulseDetectorViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PulseDetectorViewModel(application: Application): ViewModel() {

    val isTorchOn = mutableStateOf(false)
    var cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    val cameraProviderFuture = ProcessCameraProvider.getInstance(application.applicationContext)
    private var preview: Preview? = null

    val allRecords: LiveData<List<RecordEntity>>
    private val repository: RecordRepository

    //values
    var recordBpm by mutableStateOf(1)
    var isFingerOnCamera by mutableStateOf(false)
    private var isSavingRecord = false
    var currentPulseValue by mutableStateOf(0)

    init {
        val pulseMeasuresDatabase = PulseMeasuresDatabase.getInstance(application)
        val pulseMeasureDao = pulseMeasuresDatabase.recordDao()
        repository = RecordRepository(pulseMeasureDao)
        allRecords = repository.recordList
    }

    fun bindPreview(
        previewView: androidx.camera.view.PreviewView,
        lifecycleOwner: LifecycleOwner,
        cameraProvider: ProcessCameraProvider,
        finalResult: (Int) -> Unit,
        onFingerDetected: (Boolean) -> Unit,
        onPulseDetected: (Int) -> Unit
    ) {
        Log.d("PulseDetectorViewModel", "Binding preview")
        cameraProvider.unbindAll()
        if (preview == null) {
            preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val imageAnalyzer = ImageAnalysis.Builder()
                .setTargetResolution(android.util.Size(640, 480))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, PulseAnalyzer(
                        finalResult = { finalResult(it) },
                        onFingerDetected = { onFingerDetected(it) },
                        onPulseDetected = { onPulseDetected(it) }
                    ))
                }
            try {
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageAnalyzer
                )
                toggleTorch(cameraProvider, lifecycleOwner)
            } catch (exc: Exception) {
                Log.e("CameraViewModel", "Binding failed", exc)
            }
        }
    }

    fun changeRecordBpm(recBpm: Int){
        recordBpm = recBpm
    }

    fun toggleTorch(cameraProvider: ProcessCameraProvider, lifecycleOwner: LifecycleOwner) {
        Log.d("PulseDetectorViewModel", "Toggling torch")
        try {
            val camera = cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview
            )
            isTorchOn.value = !isTorchOn.value
            viewModelScope.launch(Dispatchers.IO) {
                camera.cameraControl.enableTorch(isTorchOn.value)
            }
        } catch (exc: Exception) {
            Log.e("CameraViewModel", "Torch toggle failed", exc)
        }
    }

    public override fun onCleared() {
        super.onCleared()
        changeRecordBpm(1)
        preview==null
        currentPulseValue = 0
        cameraExecutor.shutdown()
        cameraProviderFuture.cancel(true)
        Log.d("MyViewModel", "CLEARED")

    }



    fun addRecord(callback: (Long) -> Unit) {
        if (isSavingRecord) return
        isSavingRecord = true
        val record = RecordEntity(recordBpm.toString())
        viewModelScope.launch {
            val recordId = repository.addRecord(record)
            isSavingRecord = false
            callback(recordId)
        }
    }
}