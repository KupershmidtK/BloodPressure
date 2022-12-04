package com.example.bloodpressurecompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bloodpressurecompose.ui.MeasureItem

@Composable
fun MeasurementList(
    modifier: Modifier = Modifier,
    viewModel: MeasureViewModel = hiltViewModel(),
    editMeasureItem: (Long) -> Unit,
    newItemOnClick: () -> Unit) {

    val measureListItem by viewModel.allMeasurement.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = newItemOnClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = modifier.background(MaterialTheme.colors.background)
            ) {
                items(measureListItem) {
                    MeasureItem(
                        modifier = modifier,
                        measurement = it,
                        onClick = { editMeasureItem(it.id) },
                        onDeleteClick = { viewModel.deleteMeasurement(it)} )
                }
            }
        }
    }
}


