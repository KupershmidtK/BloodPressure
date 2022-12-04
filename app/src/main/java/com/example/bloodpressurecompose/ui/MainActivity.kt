package com.example.bloodpressurecompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressurecompose.ui.BloodPressureApp
import com.example.bloodpressurecompose.ui.MeasureCard
import com.example.bloodpressurecompose.ui.theme.BloodPressureComposeTheme
import com.example.bloodpressurecompose.ui.viewmodel.MeasureViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

enum class MeasureScreen(val title: String) {
    MeasureList("measure_list"),
    MeasureCard("measure_card")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodPressureComposeTheme {
                // A surface container using the 'background' color from the theme
                BloodPressureApp()
            }
        }
    }
}


