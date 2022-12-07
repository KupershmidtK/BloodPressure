package com.example.bloodpressurecompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressurecompose.ui.MeasureItem
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel

@Composable
fun MeasurementList(
    modifier: Modifier = Modifier,
    viewModel: MeasureViewModel = hiltViewModel(),
    editMeasureItem: (Long) -> Unit,
    newItemOnClick: () -> Unit) {

    val measureListItem by viewModel.allMeasurement.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
        },

        floatingActionButton = {
            FloatingActionButton(onClick = newItemOnClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },

    ) { padding ->
            LazyColumn(

            ) {
                items(measureListItem) {
                    MeasureItem(
                        modifier = modifier.padding(padding),
                        measurement = it,
                        onClick = { editMeasureItem(it.id) },
                        onDeleteClick = { viewModel.deleteMeasurement(it)} )
                }
            }

    }
}


