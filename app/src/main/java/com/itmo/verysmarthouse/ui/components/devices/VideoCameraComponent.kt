package com.itmo.verysmarthouse.ui.components.devices

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.VideoCamera

@Composable
fun VidecamComponent(videoCamera: VideoCamera) {
    Text(
        text = if (videoCamera.status) "Status: On" else "Status: Off",
        modifier = Modifier.padding(8.dp)
    )
    Switch(
        checked = videoCamera.status,
        onCheckedChange = { videoCamera.status = it }
    )
}