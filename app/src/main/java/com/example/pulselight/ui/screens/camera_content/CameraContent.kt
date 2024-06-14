package com.example.pulselight.ui.screens.camera_content

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pulselight.CameraScreen
import com.example.pulselight.ui.screens.no_permission.NoPermissionScreen
import com.example.pulselight.viewmodels.PulseDetectorViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraContent(
    hasPermission: Boolean,
    cameraPermission: PermissionState,
    vm: PulseDetectorViewModel = viewModel(),
    onRequestPermission: () -> Unit
) {
    if (hasPermission) {
        LaunchedEffect(Unit) {
            if (!cameraPermission.status.isGranted) {
                cameraPermission.launchPermissionRequest()
            } else {
                vm.startCamera()
            }
        }
        CameraScreen()
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}


@SuppressLint("RestrictedApi")
@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = context as LifecycleOwner
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    var cameraControl by remember { mutableStateOf<androidx.camera.core.CameraControl?>(null) }

    LaunchedEffect(cameraControl) {
        cameraControl?.enableTorch(true)
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraControl?.enableTorch(false)
        }
    }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            cameraProviderFuture.addListener(Runnable {
                val provider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                preview.setSurfaceProvider(previewView.surfaceProvider)

                try {
                    provider.unbindAll()
                    val camera = provider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
                    cameraControl = camera.cameraControl
                    cameraProvider = provider
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
        modifier = Modifier
            .size(200.dp)
            .background(Color.Black)
    )
}
