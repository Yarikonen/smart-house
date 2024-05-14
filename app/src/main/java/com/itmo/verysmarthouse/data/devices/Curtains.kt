package com.itmo.verysmarthouse.data.devices

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import com.itmo.verysmarthouse.data.enum.DeviceType
import com.itmo.verysmarthouse.data.interfaces.Device

class Curtains(
    override val deviceType: DeviceType,
    override val image: Painter,
    override val name: String,
    initialStatus: Boolean,
    initialOpenness: Float
) : Device {
    override var status by mutableStateOf(initialStatus)
    var openness by mutableFloatStateOf(initialOpenness)

}