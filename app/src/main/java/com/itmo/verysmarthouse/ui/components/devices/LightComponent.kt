package com.itmo.verysmarthouse.ui.components.devices

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.Light

@Composable
fun LightComponent(light: Light) {
    Text(
        text = if (light.status) "Status: On" else "Status: Off",
        modifier = Modifier.padding(8.dp)
    )
    Switch(checked = light.status, onCheckedChange = { light.status = it })
}