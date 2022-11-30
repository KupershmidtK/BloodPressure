package com.example.bloodpressurecompose

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.MeasureCard
import com.example.bloodpressurecompose.ui.theme.BloodPressureComposeTheme
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModelFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import java.util.*

enum class MeasureScreen(val title: String) {
    MeasureList("measure_list"),
    MeasureCard("measure_card")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodPressureComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: MeasureViewModel = viewModel(
                            it,
                            "MeasureViewModel",
                            MeasureViewModelFactory(
                                LocalContext.current.applicationContext as Application
                            )
                        )
                        BloodPressureApp(viewModel = viewModel)
                    }
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BloodPressureApp(modifier: Modifier = Modifier, viewModel: MeasureViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { /* todo */ },
    ) { innerPadding ->
        val allMeasurement by viewModel.allMeasurement.collectAsState(listOf())

        NavHost(
            navController = navController,
            startDestination = MeasureScreen.MeasureList.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = MeasureScreen.MeasureList.name) {
                MeasurementList(modifier, allMeasurement, navController)
            }

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
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeasurementList(modifier: Modifier = Modifier, allMeasurement: List<Measurement>, navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(MeasureScreen.MeasureCard.name + "/0") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier.background(MaterialTheme.colors.background)
        ) {
            items(allMeasurement) {
                MeasureItem(modifier = modifier, it) {
                    navController.navigate(MeasureScreen.MeasureCard.name + "/${it.id}")
                }
            }
        }
    }
}
