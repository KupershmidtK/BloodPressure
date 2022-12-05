package com.example.bloodpressurecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bloodpressurecompose.ui.BloodPressureApp
import com.example.bloodpressurecompose.ui.theme.BloodPressureComposeTheme
import dagger.hilt.android.AndroidEntryPoint

enum class MeasureScreen {
    MeasureList,
    MeasureCard
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


