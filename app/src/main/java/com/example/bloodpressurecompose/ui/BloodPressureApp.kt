package com.example.bloodpressurecompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressurecompose.MeasureScreen
import com.example.bloodpressurecompose.MeasurementList
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BloodPressureApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { /* todo */ },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MeasureScreen.MeasureList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MeasureScreen.MeasureList.name) {
                MeasurementList(
                    editMeasureItem = { navController.navigate("${MeasureScreen.MeasureCard.name}/$it") },
                    newItemOnClick = { navController.navigate("${MeasureScreen.MeasureCard.name}/0") }
                )
            }

            composable(route = MeasureScreen.MeasureCard.name + "/{measureId}") { backStackEntry ->
                val measureId = backStackEntry.arguments?.getString("measureId")?.toLong() ?: 0L
                MeasureCard(measureId = measureId)
            }
/*
            composable(route = MeasureScreen.MeasureCard.name + "/{measureId}") { backStackEntry ->
                val measureId = backStackEntry.arguments?.getString("measureId")?.toLong()
//                val measure = viewModel.getMeasurement(measureId!!)?.collectAsState(Measurement(0,Date(),0,0,0,0,0,0))

                val m = viewModel.getMeasurement(measureId!!)

                MeasureCard(modifier,
                    measurement = m,
                    addMeasurement = {
                        it.apply {
                            viewModel.addMeasurement(
                                date,
                                morningSYS, morningDIA, morningPulse,
                                eveningSYS, eveningDIA, eveningPulse
                            )
                            navController.navigate(MeasureScreen.MeasureList.name)
                        }
                    }
                )
            }
        }
    }

 */

        }
    }
}