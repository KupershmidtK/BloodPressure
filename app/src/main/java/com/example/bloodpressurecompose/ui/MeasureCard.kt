package com.example.bloodpressurecompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.ui.theme.DayColor
import com.example.bloodpressurecompose.ui.theme.NightColor
import com.example.bloodpressurecompose.ui.theme.Shapes
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
    val measure = viewModel.measure
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getMeasurement(measureId)
    }

    Scaffold(
        topBar = {
            MeasureCardHeader(
                date = measure.date,
                navigateUp = navigateUp,
                dateDialogOn = {
                    showDatePicker(
                        context = context,
                        date = measure.date,
                        updatedDate = { viewModel.updateDate(it) }
                    )
                }
            )
         },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.save)) },
                icon = { Icon(imageVector = Icons.Default.Done, contentDescription = null) },
                onClick = { onSaveClick(viewModel) }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(it)
        ) {
            //-----------------------------------------------------
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = Shapes.small,
                            spotColor = DayColor)
                ) {
                    SunIcon(Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp))
                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.morningSYS, label = stringResource(R.string.sys).uppercase(),
                        onValueChange = { value -> viewModel.updateMSys(value) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.morningDIA, label = stringResource(R.string.dia).uppercase(),
                        onValueChange = { value -> viewModel.updateMDia(value) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.morningPulse, label = stringResource(R.string.pulse).uppercase(),
                        onValueChange = { value -> viewModel.updateMPulse(value) },
                        onSaveClick = { onSaveClick(viewModel) })
                }

                Column(
                    Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = Shapes.small,
                            spotColor = NightColor)

                ) {
                    NightIcon(Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp))
                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.eveningSYS, label = stringResource(R.string.sys).uppercase(),
                        onValueChange = { value -> viewModel.updateESys(value) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.eveningDIA, label = stringResource(R.string.dia).uppercase(),
                        onValueChange = { value -> viewModel.updateEDia(value) },
                        onSaveClick = { onSaveClick(viewModel) })

                    ValueTextField(
                        Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        value = measure.eveningPulse, label = stringResource(R.string.pulse).uppercase(),
                        onValueChange = { value -> viewModel.updateEPulse(value) },
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
            IconButton(onClick = dateDialogOn ) {
                Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
            }
        }
    )
}

@Composable
fun ValueTextField(
    modifier: Modifier = Modifier,
    value: Int?,
    label: String,
    onValueChange: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
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
