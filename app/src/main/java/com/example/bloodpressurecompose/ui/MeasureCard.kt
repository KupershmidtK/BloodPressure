package com.example.bloodpressurecompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import java.text.DateFormat.getDateInstance
import java.util.*

@Composable
fun MeasureCard(
    modifier: Modifier = Modifier,
    measureId: Long = 0L,
    viewModel: MeasureViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    onSaveClick: (MeasureViewModel) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getMeasurement(measureId)
    }

    val measure = viewModel.measure


    val updatedDate: (Date) -> Unit =  {
        viewModel.updateDate(it)
    }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            MeasureCardHeader(
                date = measure.date,
                navigateUp = navigateUp,
                dateDialogOn = { showDatePicker(context, measure.date, updatedDate) })
         },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save") },
                icon = { Icon(imageVector = Icons.Default.Done, contentDescription = null) },
                onClick = { onSaveClick(viewModel) })
        }
    ) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(it)
        ) {
            //-----------------------------------------------------
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Icon(painterResource(R.drawable.ic_day), contentDescription = null)
                    ValueTextField(
                        value = measure.morningSYS, label = "SYS",
                        onValueChange = { viewModel.updateMSys(it) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        value = measure.morningDIA, label = "DIA",
                        onValueChange = { viewModel.updateMDia(it) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        value = measure.morningPulse, label = "Pulse",
                        onValueChange = { viewModel.updateMPulse(it) },
                        onSaveClick = { onSaveClick(viewModel) })
                }

                Column(Modifier.weight(1f)) {
                    Icon(painterResource(R.drawable.ic_night), contentDescription = null)
                    ValueTextField(
                        value = measure.eveningSYS, label = "SYS",
                        onValueChange = { viewModel.updateESys(it) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        value = measure.eveningDIA, label = "DIA",
                        onValueChange = { viewModel.updateEDia(it) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        value = measure.eveningPulse, label = "Pulse",
                        onValueChange = { viewModel.updateEPulse(it) },
                        onSaveClick = { onSaveClick(viewModel) })
                }
            }
        }
    }
}

@Composable
fun MeasureCardHeader(
    date: Date,
    navigateUp: () -> Unit,
    dateDialogOn: () -> Unit
) {
    TopAppBar(
        title = { Text(text = getDateInstance().format(date)) },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
         },
        actions = {
            IconButton(
                onClick = dateDialogOn ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_month_24),
                    contentDescription = null)
            }
        }
    )
}

@Composable
fun ValueTextField(
    value: Int?,
    label: String,
    onValueChange: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    OutlinedTextField(
        value = value?.toString() ?: "0",
        onValueChange = { onValueChange(it.toIntOrNull() ?: 0) },
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onSaveClick() } )
    )
}
