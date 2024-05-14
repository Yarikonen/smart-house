import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.Curtains
import com.itmo.verysmarthouse.data.devices.Door
import com.itmo.verysmarthouse.data.devices.IrrigationSystem

@Composable
fun DoorComponent(door: Door) {
    Text(
        text = if (door.status) "Status: On" else "Status: Off",
        modifier = Modifier.padding(8.dp)
    )
    Switch(
        checked = door.status,
        onCheckedChange = { door    .status = it }
    )

    if (door    .status) {
        Text(
            text = "Openness: " + door  .openness + "%",
            modifier = Modifier.padding(8.dp, 12.dp, 8.dp, 8.dp)
        )
        Slider(
            value = door    .openness,
            onValueChange = { door  .openness = it },
            valueRange = 0f..100f,
            steps = 200,
//            colors = SliderDefaults.colors(
//                activeTrackColor = Purple10,
//                activeTickColor = Purple10
//            )
        )
    }
}