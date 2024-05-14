package com.itmo.verysmarthouse.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.AirConditioner
import com.itmo.verysmarthouse.data.devices.Light
import com.itmo.verysmarthouse.data.devices.VideoCamera
import com.itmo.verysmarthouse.data.rooms.Room
import com.itmo.verysmarthouse.ui.components.templates.DeleteTemplateComponent

@Composable
fun RoomsComponent(rooms: SnapshotStateList<Room>, onClick: (Room) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(rooms.size) { index ->
            val room = rooms[index]
            Log.d("MyTag", "${room.id}")
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable { onClick(room) },
                border = BorderStroke(1.dp,Color.Black),
                shape = AbsoluteCutCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF93B33B)),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    DeleteTemplateComponent(
                        onClick = {
                            rooms.remove(room)
                        }
                    )
                    Image(
                        painter = room.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp)
                    )
                    Text(
                        text = room.name,
                        modifier = Modifier.padding(8.dp),
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    room.devices.forEach { device ->
                        when (device) {
                           is AirConditioner, is Light, is VideoCamera -> Text(
                                text = if (device.status) device.name + ": " + "Enabled" else device.name + ": " + "Disabled",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
