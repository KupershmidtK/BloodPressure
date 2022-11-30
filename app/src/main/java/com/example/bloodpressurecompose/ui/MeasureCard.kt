package com.example.bloodpressurecompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
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
                measurement: Measurement?,
                addMeasurement: (Measurement) -> Unit
) {
    val measureDate by remember { mutableStateOf(measurement?.date) }

    var mSysVal by remember { mutableStateOf(measurement?.morningSYS?.toString() ?: "") }
    val mSysValChange = { text: String -> if (text.isDigitsOnly()) mSysVal = text }
    var mDiaVal by remember { mutableStateOf(measurement?.morningDIA?.toString() ?: "") }
    val mDiaValChange = { text: String -> if (text.isDigitsOnly()) mDiaVal = text }
    var mPulseVal by remember { mutableStateOf(measurement?.morningPulse?.toString() ?: "") }
    val mPulseValChange = { text: String -> if (text.isDigitsOnly()) mPulseVal = text }

    var eSysVal by remember { mutableStateOf(measurement?.eveningSYS?.toString() ?: "") }
    val eSysValChange = { text: String -> if (text.isDigitsOnly())  eSysVal = text }
    var eDiaVal by remember { mutableStateOf(measurement?.eveningDIA?.toString() ?: "") }
    val eDiaValChange = { text: String -> if (text.isDigitsOnly())  eDiaVal = text }
    var ePulseVal by remember { mutableStateOf(measurement?.eveningPulse?.toString() ?: "") }
    val ePulseValChange = { text: String -> if (text.isDigitsOnly())  ePulseVal = text }

    val onSaveBtnClick1 = {
        val msm = Measurement(
            date = measureDate!!,
            morningSYS = mSysVal.toInt(),
            morningDIA = mDiaVal.toInt(),
            morningPulse = mPulseVal.toInt(),
            eveningSYS = eSysVal.toInt(),
            eveningDIA = eDiaVal.toInt(),
            eveningPulse = ePulseVal.toInt(),
        )
        addMeasurement(msm)
    }

    Column(modifier = modifier.fillMaxSize()) {
            MeasureCardHeader(modifier, measureDate?: Date())

            MeasureCardData(modifier, 
                R.drawable.ic_day, 
                mSysVal, mSysValChange,
                mDiaVal, mDiaValChange,
                mPulseVal, mPulseValChange,
                )

            MeasureCardData(modifier,
                R.drawable.ic_night,
                eSysVal, eSysValChange,
                eDiaVal, eDiaValChange,
                ePulseVal, ePulseValChange,
            )

        Row() {
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }

            Button(onClick = onSaveBtnClick1) {
                Text(text = "Save")
            }
        }
    }
}

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
            onValueChange = onSysChange,
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

//@Preview
//@Composable
//fun MeasureCardPreview() {
//    BloodPressureComposeTheme() {
//        MeasureCard(measurement = Measurement(0, Date(), 120, 60, 70, 122, 62, 73))
//    }
//}