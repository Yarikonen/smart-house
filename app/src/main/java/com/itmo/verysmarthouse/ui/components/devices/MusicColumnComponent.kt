package com.itmo.verysmarthouse.ui.components.devices

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.MusicColumn

@Composable
fun MusicColumnComponent(musicColumn: MusicColumn) {
    Text(
        text = if (musicColumn.status) "Status: One" else "Status: Off",
        modifier = Modifier.padding(8.dp)
    )
    Switch(
        checked = musicColumn.status,
        onCheckedChange = { musicColumn.status = it }
    )

    if (musicColumn.status) {
        Text(
            text = "Volume: " + musicColumn.volume + "%",
            modifier = Modifier.padding(8.dp, 12.dp, 8.dp, 8.dp)
        )
        Slider(
            value = musicColumn.volume,
            onValueChange = { musicColumn.volume = it },
            valueRange = 0f..100f,
            steps = 200,
//            colors = SliderDefaults.colors(
//                activeTrackColor = Purple10,
//                activeTickColor = Purple10
//            )
        )
    }
}