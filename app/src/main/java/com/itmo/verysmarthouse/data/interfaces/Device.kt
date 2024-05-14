package com.itmo.verysmarthouse.data.interfaces

import androidx.compose.ui.graphics.painter.Painter
import com.itmo.verysmarthouse.data.enum.DeviceType

interface Device{
    val deviceType: DeviceType
    val image: Painter
    val name: String
    var status: Boolean
}