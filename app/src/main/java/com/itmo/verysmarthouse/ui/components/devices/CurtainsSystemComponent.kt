import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itmo.verysmarthouse.data.devices.Curtains
import com.itmo.verysmarthouse.data.devices.IrrigationSystem

@Composable
fun CurtainsSystemComponent(curtains: Curtains) {
    Text(
        text = if (curtains.status) "Status: On" else "Status: Off",
        modifier = Modifier.padding(8.dp)
    )
    Switch(
        checked = curtains.status,
        onCheckedChange = { curtains.status = it }
    )

    if (curtains.status) {
        Text(
            text = "Openness: " + curtains.openness + "%",
            modifier = Modifier.padding(8.dp, 12.dp, 8.dp, 8.dp)
        )
        Slider(
            value = curtains.openness,
            onValueChange = { curtains.openness = it },
            valueRange = 0f..100f,
            steps = 200,
//            colors = SliderDefaults.colors(
//                activeTrackColor = Purple10,
//                activeTickColor = Purple10
//            )
        )
    }
}