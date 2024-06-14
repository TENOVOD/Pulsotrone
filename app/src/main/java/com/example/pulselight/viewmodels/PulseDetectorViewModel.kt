package com.example.pulselight.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PulseDetectorViewModel: ViewModel() {
    private val mutableCameraState = MutableStateFlow<CameraState>(CameraState.Initial)
    val cameraState: StateFlow<CameraState> = mutableCameraState

    init {
        startCamera()
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