package com.itmo.verysmarthouse.data.devices

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import com.itmo.verysmarthouse.data.enum.DeviceType
import com.itmo.verysmarthouse.data.interfaces.Device

class MusicColumn(
    initialDeviceType: DeviceType,
    override val image: Painter,
    override val name: String,
    initialStatus: Boolean,
    initialVolume: Float,
) : Device {
    override val deviceType by mutableStateOf(initialDeviceType)
    override var status by mutableStateOf(initialStatus)
    var volume by mutableFloatStateOf(initialVolume)
}