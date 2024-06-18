package com.example.pulselight.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.pulselight.R


import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.LinearProgressTool
import com.example.pulselight.ui.elements.labelsAndTexts.DetectionFingerLabel
import com.example.pulselight.ui.elements.labelsAndTexts.InstructionText
import com.example.pulselight.ui.elements.labelsAndTexts.LabelBpmButton
import com.example.pulselight.ui.elements.labelsAndTexts.LabelBpmOnButton

import com.example.pulselight.ui.screens.no_permission.NoPermissionScreen
import com.example.pulselight.viewmodels.PulseDetectorViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun HeartMeasuringScreen(navController: NavController, vm: PulseDetectorViewModel) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(permission = Manifest.permission.CAMERA)

    val screenHeight = getScreenHeightInDp()
    val heartShadow: Painter = painterResource(id = R.drawable.heart_shadow)
    val heartWithGlare: Painter = painterResource(id = R.drawable.heart_with_glare)
    val fingerOnCamera: Painter = painterResource(id = R.drawable.finger_on_camera)

    var finalResult by remember { mutableStateOf(vm.recordBpm) }
    var pulse by remember { mutableStateOf(vm.currentPulseValue) }
    var isMeasuring by remember {
        mutableStateOf(vm.isFingerOnCamera)
    }
    Log.d("RESRES","BATYA YA STARAYUS")
    if (finalResult != 0) {
        vm.recordBpm = finalResult
        finalResult = 0
        vm.addRecord { newRecordId ->
            navController.navigate("ResultScreen/$newRecordId")
        }
        vm.cameraExecutor.shutdown()
    }


    HomepageBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Button(onClick = { }) {
                Text(text = "CHANGE")
            }
            CameraContent(
                hasPermission = cameraPermissionState.status.isGranted,
                vm = vm,
                onPulseDetected = { pulse = it },
                onFingerDetected = { isMeasuring = it },
                finalResult = { finalResult = it },
                onRequestPermission = cameraPermissionState::launchPermissionRequest,

                )

            Spacer(modifier = Modifier.height(10.dp))
            if (isMeasuring) {
                DetectionFingerLabel(textId = R.string.measurement_is_in_progress)
                Spacer(modifier = Modifier.height(10.dp))
                InstructionText(textId = R.string.measuring_your_pulse)
            } else {
                DetectionFingerLabel(textId = R.string.missing_finger)
                Spacer(modifier = Modifier.height(10.dp))
                InstructionText(textId = R.string.put_finger_to_camera)
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .offset(y = (-screenHeight) / 16),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 230.dp)
                        .fillMaxWidth()
                        .height(170.dp),
                    painter = heartShadow,
                    contentDescription = "hearts shadow"
                )

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = heartWithGlare,
                    contentDescription = "Heart"
                )

                LabelBpmOnButton(modifier = Modifier.padding(top = 100.dp))
                LabelBpmButton(bpmCount = pulse.toString())
            }
            if (isMeasuring) {
                LinearProgressTool(animationDuration = 20000)
            } else {
                Image(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .padding(start = 30.dp)
                        .fillMaxWidth()
                        .offset(y = (-screenHeight) / 10),

                    painter = fingerOnCamera,
                    contentDescription = "Finger on phones camera"
                )
            }

        }

    }

}

@Composable
fun CameraContent(
    finalResult: (Int) -> Unit,
    onFingerDetected: (Boolean) -> Unit,
    onPulseDetected: (Int) -> Unit,
    hasPermission: Boolean,
    vm: PulseDetectorViewModel,
    onRequestPermission: () -> Unit,
) {
    if (hasPermission) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Black)
        ) {
            CameraPreview(vm, finalResult, onFingerDetected, onPulseDetected)
        }

    } else {
        NoPermissionScreen(onRequestPermission)
    }
}


@SuppressLint("RestrictedApi")
@Composable
fun CameraPreview(
    vm: PulseDetectorViewModel,
    finalResult: (Int) -> Unit,
    onFingerDetected: (Boolean) -> Unit,
    onPulseDetected: (Int) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProvider = vm.cameraProviderFuture.get()


    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                vm.bindPreview(
                    this,
                    lifecycleOwner,
                    cameraProvider,
                    finalResult,
                    onFingerDetected,
                    onPulseDetected
                )
            }
        },
        modifier = Modifier
            .size(200.dp)
            .background(Color.Black)
    )

}


