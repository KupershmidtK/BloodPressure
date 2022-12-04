package com.example.bloodpressurecompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bloodpressurecompose.data.IMeasureRepository
import com.example.bloodpressurecompose.data.MeasureRepositoryImpl
import com.example.bloodpressurecompose.data.MeasureDB
import com.example.bloodpressurecompose.data.MeasureDao
import com.example.bloodpressurecompose.model.Measurement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MeasureViewModel @Inject constructor(private val repo: IMeasureRepository) : ViewModel() {
    val allMeasurement = repo.allMeasurement()

    fun getMeasurement(id: Long): Flow<Measurement> {
        return repo.getMeasurement(id)
    }

    fun addMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            repo.insertMeasure(measurement)
        }
    }

    fun updateMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            repo.updateMeasure(measurement)
        }
    }

    fun deleteMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            repo.deleteMeasure(measurement)
        }
    }
}