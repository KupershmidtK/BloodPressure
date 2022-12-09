package com.example.bloodpressurecompose

import android.graphics.drawable.Icon
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.DeleteIcon
import com.example.bloodpressurecompose.ui.MeasureItem
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.internal.DoubleCheck.lazy

@OptIn(ExperimentalMaterialApi::class)
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
        LazyColumn {
            items(measureListItem, {measureListItem: Measurement -> measureListItem.id}) { item ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { false }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.2f) },
                    background = {
                        val color by animateColorAsState (
                            targetValue = when (dismissState.targetValue) {
                                DismissValue.Default -> MaterialTheme.colors.background
                                else -> Color.Red
                            }
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .background(color)
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            IconButton(onClick = { viewModel.deleteMeasurement(item) }) {
                                DeleteIcon()
                            }
                        }
                    }
                ) {
                    MeasureItem (
                        modifier = modifier.padding(padding),
                        measurement = item,
                        onClick = { editMeasureItem(item.id) },
                        onDeleteClick = { viewModel.deleteMeasurement(item) }
                    )
                }
            }
        }
    }
}


