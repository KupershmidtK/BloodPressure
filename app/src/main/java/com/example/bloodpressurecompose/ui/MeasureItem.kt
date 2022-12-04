package com.example.bloodpressurecompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.DateFormat

@Composable
fun MeasureItem(
    modifier: Modifier = Modifier,
    measurement: Measurement,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(true, onClick = onClick)
    ) {

        Column {
            Text(
                text = DateFormat.getDateInstance().format(measurement.date),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Row {
                Column {
                    Row(modifier.padding(4.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_day),
                            contentDescription = "Morning",
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "SYS", modifier = modifier.padding(end = 4.dp))
                        Text(
                            text = measurement.morningSYS.toString(),
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "DIA", modifier = modifier.padding(end = 4.dp))
                        Text(
                            text = measurement.morningDIA.toString(),
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "PULSE", modifier = modifier.padding(end = 4.dp))
                        Text(text = measurement.morningPulse.toString())
                    }
                    Row(modifier.padding(4.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_night),
                            contentDescription = "Evening",
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "SYS", modifier = modifier.padding(end = 4.dp))
                        Text(
                            text = measurement.eveningSYS.toString(),
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "DIA", modifier = modifier.padding(end = 4.dp))
                        Text(
                            text = measurement.eveningDIA.toString(),
                            modifier = modifier.padding(end = 8.dp)
                        )
                        Text(text = "PULSE", modifier = modifier.padding(end = 4.dp))
                        Text(
                            text = measurement.eveningPulse.toString(),
                            modifier = modifier.padding(end = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        //modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}