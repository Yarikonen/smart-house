package com.itmo.verysmarthouse.ui.components.devices

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.Light
import com.itmo.verysmarthouse.data.devices.Plug

@Composable
fun PlugComponent(plug: Plug) {
    Text(
        text = if (plug.status) "Status: Connected" else "Status: Disconnected",
        modifier = Modifier.padding(8.dp)
    )
    Switch(checked = plug.status, onCheckedChange = { plug.status = it })
}