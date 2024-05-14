package com.example.smarthouse.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.itmo.verysmarthouse.data.rooms.Room
import com.itmo.verysmarthouse.ui.components.RoomInfoComponent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomScreen(room: Room){
    RoomInfoComponent(devices = room.devices)
}