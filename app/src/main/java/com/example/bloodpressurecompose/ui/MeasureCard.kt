package com.example.bloodpressurecompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bloodpressurecompose.MeasureScreen
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.theme.BloodPressureComposeTheme
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import java.text.DateFormat.getDateInstance
import java.util.*

@Composable
fun MeasureCard(modifier: Modifier = Modifier,
                measureId: Long = 0L,
                viewModel: MeasureViewModel = hiltViewModel(),
//                addMeasurement: (Measurement) -> Unit
) {
//    val measure by viewModel.getMeasurement(measereId).collectAsState(initial = Measurement())
//    var measure by if (measureId == 0L) {
//        remember { mutableStateOf(Measurement()) }
//    } else {
//
//    }

    var measure = if (measureId == 0L) {
        remember { mutableStateOf(Measurement()) }
    } else {
        viewModel.getMeasurement(measureId).collectAsState(initial = Measurement())
    }.value

    Column(modifier = modifier.fillMaxSize()) {
            MeasureCardHeader(modifier, measure.date)

        //-----------------------------------------------------
        Icon(painterResource(R.drawable.ic_day), contentDescription = null)
        OutlinedTextField(
            value = measure.morningSYS?.toString() ?: "0",
            onValueChange = { measure = measure.copy(morningSYS = it.toIntOrNull()) },
            label = { Text(text = "SYS") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = measure.morningDIA?.toString() ?: "0",
            onValueChange = { measure.morningDIA = it.toIntOrNull() },
            label = { Text(text = "DIA") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = measure.morningPulse?.toString() ?: "0",
            onValueChange = { measure.morningPulse = it.toIntOrNull() },
            label = { Text(text = "Pulse") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(32.dp))
        //-----------------------------------------------------------------------
        Icon(painterResource(R.drawable.ic_night), contentDescription = null)
        OutlinedTextField(
            value = measure.eveningSYS?.toString() ?: "0",
            onValueChange = { measure = measure.copy(eveningSYS = it.toInt()) },
            label = { Text(text = "SYS") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = measure.eveningDIA?.toString() ?: "0",
            onValueChange = { measure.eveningDIA = it.toIntOrNull() },
            label = { Text(text = "DIA") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = measure.eveningPulse?.toString() ?: "0",
            onValueChange = { measure.eveningPulse = it.toIntOrNull() },
            label = { Text(text = "Pulse") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        //---------------------------------------------------------------------------

        Row() {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    if (measureId == 0L )
                        viewModel.addMeasurement(measure)
                    else viewModel.updateMeasurement(measure)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}

/*
@Composable
fun MeasureCardData(modifier: Modifier, @DrawableRes iconId: Int,
                    sys: String, onSysChange: (String) -> Unit,
                    dia: String, onDiaChange: (String) -> Unit,
                    pulse: String, onPulseChange: (String) -> Unit,
) {
    Column() {
        Icon(painterResource(iconId), contentDescription = null)

        TextField(
            value = sys,
            onValueChange = { sys = it },
            label = { Text(text = "SYS") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = dia,
            onValueChange = onDiaChange,
            label = { Text(text = "DIA") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = pulse,
            onValueChange = onPulseChange,
            label = { Text(text = "PULSE") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}


 */

@Composable
fun MeasureCardHeader(modifier: Modifier, date: Date) {
    Column() {
        TextField(
            modifier = modifier.clickable {  },
            value = getDateInstance().format(date),
            onValueChange = {},
            enabled = false,
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_calendar_month_24),
                    contentDescription = null)
            }
        )
    }
}
