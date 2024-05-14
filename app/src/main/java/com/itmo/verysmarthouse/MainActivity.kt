package com.itmo.verysmarthouse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarthouse.ui.screens.MainScreen
import com.example.smarthouse.ui.screens.RoomScreen
import com.itmo.verysmarthouse.data.devices.AirConditioner
import com.itmo.verysmarthouse.data.devices.Curtains
import com.itmo.verysmarthouse.data.devices.Door
import com.itmo.verysmarthouse.data.devices.IrrigationSystem
import com.itmo.verysmarthouse.data.devices.Light
import com.itmo.verysmarthouse.data.devices.VideoCamera
import com.itmo.verysmarthouse.data.devices.WashingMachine
import com.itmo.verysmarthouse.data.enum.DeviceType
import com.itmo.verysmarthouse.data.enum.WashingType
import com.itmo.verysmarthouse.data.rooms.Room
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lightPainter = painterResource(id = R.drawable.light)
            val curtainsPainter = painterResource(id = R.drawable.curtain)
            val washingMachinePainter = painterResource(id = R.drawable.washing_machine)
            val conditionerPainter = painterResource(id = R.drawable.airconditioner)
            val videcamPainter = painterResource(id = R.drawable.camera)
            val irrigationSystemPainter = painterResource(id = R.drawable.irrigation)
            val doorPainter = painterResource(id = R.drawable.bline)
            val lightName = "Light"
            val videoCamName = "CCTV"

            val garage =  Room(
            painterResource(id = R.drawable.garage),
            "garage",
            remember {
                mutableStateListOf(
                    IrrigationSystem(
                        DeviceType.IRRIGATION_SYSTEM,
                        irrigationSystemPainter,
                        name = "Irrigation system",
                        true,
                        30f
                    ),
                    Light(DeviceType.LIGHT, lightPainter, lightName, true),
                    VideoCamera(DeviceType.VIDEO_CAMERA, videcamPainter, videoCamName, true),
                    Door(DeviceType.DOOR,doorPainter, name="Garage door", true, 30f)
                )
            }
        )

            val livingRoom = Room(
                painterResource(id = R.drawable.livingroom),
                "living room",
                remember {
                    mutableStateListOf(
                        AirConditioner(
                            DeviceType.AIR_CONDITIONER,
                            conditionerPainter,
                            name = "Conditioner",
                            false,
                            initialTemperature = 10.0f
                        ),
                        Light(DeviceType.LIGHT, lightPainter, lightName, true),
                        VideoCamera(DeviceType.VIDEO_CAMERA, videcamPainter, videoCamName, true),
                    )
                }
            )
            val bedroom = Room(
                painterResource(id = R.drawable.bedroom),
                "Bedroom",
                remember {
                    mutableStateListOf(
                        AirConditioner(
                            DeviceType.AIR_CONDITIONER,
                            conditionerPainter,
                            name = "Conditioner",
                            true,
                            initialTemperature = 10.0f
                        ),
                        Light(DeviceType.LIGHT, lightPainter, lightName, true),
                        Curtains(
                            DeviceType.CURTAINS,
                            curtainsPainter,
                            "Curtains",
                            true,
                            initialOpenness = 100.0f

                        ),
                        VideoCamera(DeviceType.VIDEO_CAMERA, videcamPainter, "CCTV", true),

                        )
                }
            )
            val kitchen = Room(
                painterResource(id = R.drawable.kitchen),
                "Kitchen",
                remember {
                    mutableStateListOf(
                        AirConditioner(
                            DeviceType.AIR_CONDITIONER,
                            conditionerPainter,
                            name = "Conditioner",
                            false,
                            initialTemperature = 10.0f
                        ),
                        Light(DeviceType.LIGHT, lightPainter, lightName, true),
                        WashingMachine(
                            DeviceType.WASHING_MACHINE,
                            washingMachinePainter,
                            "Washing machine",
                            true,
                            WashingType.SOFT,
                            LocalTime.of(8, 30)
                        ),
                        VideoCamera(DeviceType.VIDEO_CAMERA, videcamPainter, videoCamName, true),

                        )
                }
            )
            val rooms = remember {
                mutableStateListOf(livingRoom, bedroom, kitchen,garage)
            }
            val navController = rememberNavController()
            val mainScreen = "main"
            NavHost(
                navController = navController,
                startDestination = mainScreen
            ) {
                composable(mainScreen) {
                    MainScreen(rooms = rooms) { room ->
                        val route = "room/${room.id}"
                        navController.navigate(route)
                    }
                }
                repeat(100) { index ->
                    val route = "room/${index}"
                    composable(route = route) {
                        RoomScreen(room = rooms[index])
                    }
                }
            }
        }
    }
}