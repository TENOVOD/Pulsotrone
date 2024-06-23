package com.example.pulselight.ui.screens.no_permission


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.R
import com.example.pulselight.ui.elements.buttons.RegularButton
import com.example.pulselight.ui.elements.labelsAndTexts.DetectionFingerLabel

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {
    NoPermissionContent(
        onRequestPermission = onRequestPermission
    )
}

@Composable
private fun NoPermissionContent(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DetectionFingerLabel(R.string.get_permission_message)
        Spacer(modifier = Modifier.height(16.dp))
        RegularButton(onClickAction = onRequestPermission, buttonText = stringResource(id = R.string.get_permission_button) )

    }
}