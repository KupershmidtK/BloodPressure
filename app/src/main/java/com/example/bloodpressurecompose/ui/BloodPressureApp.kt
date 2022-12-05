package com.example.bloodpressurecompose.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressurecompose.MeasureScreen
import com.example.bloodpressurecompose.MeasurementList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BloodPressureApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MeasureScreen.MeasureList.name,
    ) {
        composable(route = MeasureScreen.MeasureList.name) {
            MeasurementList(
                editMeasureItem = { navController.navigate("${MeasureScreen.MeasureCard.name}/$it") },
                newItemOnClick = { navController.navigate("${MeasureScreen.MeasureCard.name}/0") }
            )
        }


        composable(route = MeasureScreen.MeasureCard.name + "/{measureId}") { backStackEntry ->
            val measureId = backStackEntry.arguments?.getString("measureId")?.toLong() ?: 0L
            MeasureCard(
                measureId = measureId,
                navigateUp = { navController.popBackStack() },
                onSaveClick = { viewModel ->
                    if (measureId == 0L) {
                        viewModel.addMeasurement(viewModel.measure)
                    } else {
                        viewModel.updateMeasurement(viewModel.measure)
                    }
                    navController.navigate(MeasureScreen.MeasureList.name)
                })
        }
    }

}