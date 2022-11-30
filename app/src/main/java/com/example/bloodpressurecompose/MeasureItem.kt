package com.example.bloodpressurecompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.theme.BloodPressureComposeTheme

@Composable
fun MeasureItem(modifier: Modifier = Modifier, measurement: Measurement, onClick: () -> Unit) {
    Column(modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable(true, onClick = onClick)
    ) {
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = measurement.date.toString())
        }
        Row(modifier.padding(4.dp)) {
            Icon(painter = painterResource(R.drawable.ic_day),
                contentDescription = "Morning",
                modifier = modifier.padding(end = 8.dp))
            Text(text = "SYS", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.morningSYS.toString(), modifier = modifier.padding(end = 8.dp))
            Text(text = "DIA", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.morningDIA.toString(), modifier = modifier.padding(end = 8.dp))
            Text(text = "PULSE", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.morningPulse.toString())
        }
        Row(modifier.padding(4.dp)) {
            Icon(painter = painterResource(R.drawable.ic_night),
                contentDescription = "Evening",
                modifier = modifier.padding(end = 8.dp))
            Text(text = "SYS", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.eveningSYS.toString(), modifier = modifier.padding(end = 8.dp))
            Text(text = "DIA", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.eveningDIA.toString(), modifier = modifier.padding(end = 8.dp))
            Text(text = "PULSE", modifier = modifier.padding(end = 4.dp))
            Text(text = measurement.eveningPulse.toString(), modifier = modifier.padding(end = 8.dp))
        }
    }
}
