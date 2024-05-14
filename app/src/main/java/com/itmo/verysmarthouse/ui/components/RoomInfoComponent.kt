package com.itmo.verysmarthouse.ui.components

import android.annotation.SuppressLint
import android.os.Build
import android.provider.MediaStore.Video
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.R
import com.itmo.verysmarthouse.data.devices.AirConditioner
import com.itmo.verysmarthouse.data.devices.IrrigationSystem
import com.itmo.verysmarthouse.data.devices.Light
import com.itmo.verysmarthouse.data.devices.VideoCamera
import com.itmo.verysmarthouse.data.devices.WashingMachine
import com.itmo.verysmarthouse.data.enum.DeviceType
import com.itmo.verysmarthouse.data.enum.WashingType
import com.itmo.verysmarthouse.data.interfaces.Device
import com.itmo.verysmarthouse.ui.components.devices.AirConditionerComponent
import com.itmo.verysmarthouse.ui.components.devices.IrrigationSystemComponent
import com.itmo.verysmarthouse.ui.components.devices.LightComponent
import com.itmo.verysmarthouse.ui.components.devices.VidecamComponent
import com.itmo.verysmarthouse.ui.components.devices.WashingMachineComponent
import com.itmo.verysmarthouse.ui.components.templates.CreateTemplateComponent
import com.itmo.verysmarthouse.ui.components.templates.DeviceTemplateComponent
import com.itmo.verysmarthouse.ui.components.templates.DropdownMoreVertTemplate
import java.time.LocalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomInfoComponent(devices: SnapshotStateList<Device>) {
    val dialogOpen = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
                    title = { Text("Устройства") },
                    actions = {
                        DropdownMoreVertTemplate {
                            dialogOpen.value = true
                        }
                    },
                )
            }
        },
        content = {
            val suggestions = DeviceType.entries.toTypedArray()
            var selectedElement by remember {
                mutableStateOf(DeviceType.VIDEO_CAMERA)
            }
            val airConditionerPainter = painterResource(id = R.drawable.airconditioner)

            val irrigationSystemPainter = painterResource(id = R.drawable.irrigation)

            val videcamPainter = painterResource(id = R.drawable.camera)
            
            val lightPainter = painterResource(id = R.drawable.light)
            
            val washingMachinePainter = painterResource(id = R.drawable.kitchen)


            CreateTemplateComponent(
                onClick = { name ->
                    val device = when (selectedElement) {
                        DeviceType.AIR_CONDITIONER -> AirConditioner(
                            DeviceType.AIR_CONDITIONER,
                            airConditionerPainter,
                            name,
                            false,
                            15f
                        )



                        DeviceType.IRRIGATION_SYSTEM -> IrrigationSystem(
                            DeviceType.IRRIGATION_SYSTEM,
                            irrigationSystemPainter,
                            name,
                            false,
                            30f
                        )



                        DeviceType.VIDEO_CAMERA -> VideoCamera(
                            DeviceType.VIDEO_CAMERA,
                            videcamPainter,
                            name,
                            false
                        )
                        
                        DeviceType.LIGHT -> Light(
                            DeviceType.VIDEO_CAMERA,
                            lightPainter,
                            name,
                            false
                        )

                        DeviceType.WASHING_MACHINE -> WashingMachine(
                            DeviceType.WASHING_MACHINE,
                            washingMachinePainter,
                            name,
                            false,
                            WashingType.HARD,
                            LocalTime.of(15, 30)
                        )

                    }
                    devices.add(device)
                    selectedElement = DeviceType.VIDEO_CAMERA
                },
                dialogOpen = dialogOpen,
                suggestions = suggestions,
                selectedElement = selectedElement,
                getStringValue = { it.value },
                onValueChange = { selectedElement = it },
                labelName = "Device name",
                labelType = "Device type"
            )
            Column(
                modifier = Modifier.padding(top = 60.dp)
            )
            {
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    devices.forEach { device ->
                        item {
                            DeviceTemplateComponent(
                                deviceType = device.deviceType,
                                initialStatus = device.status,
                                image = device.image,
                                name = device.name,
                                onDelete = { devices.remove(device) },
                                content = {
                                    when (device) {
                                        is AirConditioner -> AirConditionerComponent(airConditioner = device)

                                        is IrrigationSystem -> IrrigationSystemComponent(
                                            irrigationSystem = device
                                        )
                                        is VideoCamera -> VidecamComponent(videoCamera = device)
                                        
                                        is Light -> LightComponent(light = device)
                                        
                                        is WashingMachine -> WashingMachineComponent(washingMachine = device)
                                    }
                                })
                        }
                    }
                }
            }
        }
    )
}

