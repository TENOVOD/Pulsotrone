package com.example.pulselight.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pulselight.R


import com.example.pulselight.ui.backgrounds.HomepageBackground
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.LinearProgressTool
import com.example.pulselight.ui.elements.labelsAndTexts.DetectionFingerLabel
import com.example.pulselight.ui.elements.labelsAndTexts.InstructionText
import com.example.pulselight.ui.elements.labelsAndTexts.LabelBpmButton
import com.example.pulselight.ui.elements.labelsAndTexts.LabelBpmOnButton
import com.example.pulselight.ui.screens.camera_content.CameraContent
import com.example.pulselight.viewmodels.PulseDetectorViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun HeartMeasuringScreen() {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(permission = Manifest.permission.CAMERA)
    val screenHeight= getScreenHeightInDp()
    val heartShadow: Painter = painterResource(id = R.drawable.heart_shadow)
    val heartWithGlare: Painter = painterResource(id = R.drawable.heart_with_glare)
    val fingerOnCamera: Painter = painterResource(id = R.drawable.finger_on_camera)

    val finalResult by remember { mutableStateOf(0) }
    var pulse by remember { mutableStateOf(0) }
    var isMeasuring by remember {
        mutableStateOf(false)
    }


    HomepageBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            CameraContent(
                finalResult,
                hasPermission = cameraPermissionState.status.isGranted,
                cameraPermissionState,
                onRequestPermission = cameraPermissionState::launchPermissionRequest,
                onFingerDetected = {isMeasuring =it},
                onPulseDetected = {pulse=it}
            )

            Spacer(modifier = Modifier.height(10.dp))
            if(isMeasuring){
                DetectionFingerLabel(textId = R.string.measurement_is_in_progress)
                Spacer(modifier = Modifier.height(10.dp))
                InstructionText(textId = R.string.measuring_your_pulse)
            }else{
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
                LabelBpmButton(bpmCount=pulse.toString())
            }
            if(isMeasuring){
                LinearProgressTool(animationDuration=20000)
            }else{
                Image(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .padding(start = 30.dp)
                        .fillMaxWidth()
                        .offset(y = (-screenHeight) / 10)
                    ,

                    painter = fingerOnCamera,
                    contentDescription = "Finger on phones camera"
                )
            }

        }

    }

}

