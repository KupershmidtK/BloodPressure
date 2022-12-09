package com.example.bloodpressurecompose.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.theme.Shapes
import java.text.DateFormat

@Composable
fun MeasureValue(@StringRes label: Int, value: Int?) {
    Row(
        modifier = Modifier
            .padding(end = 16.dp),
    ) {
        Text(
            text = stringResource(label),
        )
        Text(
            text = value?.toString() ?: "-",
            modifier = Modifier
                .width(32.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primaryVariant)
    }

}

@Composable
fun MeasureItem(
    modifier: Modifier = Modifier,
    measurement: Measurement,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(true, onClick = onClick),
        shape = Shapes.medium,
        elevation = 4.dp
    ) {
        Column(modifier = modifier.padding(4.dp)) {
            Text(
                text = DateFormat.getDateInstance().format(measurement.date),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant
            )

            Row {
                Column {
                    Row(modifier.padding(4.dp)) {
                        SunIcon(modifier.padding(start = 4.dp, end = 16.dp))
                        MeasureValue(label = R.string.sys, value = measurement.morningSYS)
                        MeasureValue(label = R.string.dia, value = measurement.morningDIA)
                        MeasureValue(label = R.string.pulse, value = measurement.morningPulse)
                    }
                    Row(modifier.padding(4.dp)) {
                        NightIcon(modifier.padding(start = 4.dp, end = 16.dp))
                        MeasureValue(label = R.string.sys, value = measurement.eveningSYS)
                        MeasureValue(label = R.string.dia, value = measurement.eveningDIA)
                        MeasureValue(label = R.string.pulse, value = measurement.eveningPulse)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                /*
                IconButton(onClick = onDeleteClick) {
                    DeleteIcon(Modifier.fillMaxHeight().align(Alignment.Bottom))
                }

                 */
            }
        }
    }
}