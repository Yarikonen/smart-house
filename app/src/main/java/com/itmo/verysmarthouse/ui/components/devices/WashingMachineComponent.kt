package com.itmo.verysmarthouse.ui.components.devices

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.Plug
import com.itmo.verysmarthouse.data.devices.WashingMachine
import com.itmo.verysmarthouse.data.enum.WashingType
import com.itmo.verysmarthouse.ui.components.templates.DropdownInputTemplate
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WashingMachineComponent(washingMachine: WashingMachine) {
    Text(
        text = if (washingMachine.status) "Status: In work" else "Status: Disabled",
        modifier = Modifier.padding(8.dp)
    )
    Switch(checked = washingMachine.status, onCheckedChange = { washingMachine.status = it })

    if (washingMachine.status) {
        Text(
            text = "Begin to wash time: " + washingMachine.time,
            modifier = Modifier.padding(8.dp, 12.dp, 8.dp, 8.dp)
        )

        val clockState = rememberSheetState()
        clockState.visible = false

        ClockDialog(state = clockState,
            config = ClockConfig(
                defaultTime = LocalTime.of(8, 30)
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                washingMachine.time = LocalTime.of(hours, minutes)
            })

        Column {
            Button(
                onClick = {
                    clockState.show()
                }) {
                Text(text = "Set time")
            }
        }
        DropdownInputTemplate(
            suggestions = WashingType.entries.toTypedArray(),
            selectedElement = washingMachine.washingType,
            getStringValue = { it.value },
            onValueChange = { washingMachine.washingType = it },
            label = "Choose washing type"
        )
    }
}