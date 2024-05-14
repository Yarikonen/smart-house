package com.itmo.verysmarthouse.data.devices

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import com.itmo.verysmarthouse.data.enum.DeviceType
import com.itmo.verysmarthouse.data.enum.WashingType
import com.itmo.verysmarthouse.data.interfaces.Device
import java.time.LocalTime

class WashingMachine(
    initialDeviceType: DeviceType,
    override val image: Painter,
    override val name: String,
    initialStatus: Boolean,
    initialWashingType: WashingType,
    initialTime: LocalTime
) :
    Device {
    override var status by mutableStateOf(initialStatus)
    override val deviceType by mutableStateOf(initialDeviceType)
    var time by mutableStateOf(initialTime)
    var washingType by mutableStateOf(initialWashingType)
}