package com.example.bloodpressurecompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressurecompose.data.IMeasureRepository
import com.example.bloodpressurecompose.model.Measurement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MeasureViewModel @Inject constructor(private val repo: IMeasureRepository) : ViewModel() {
    val allMeasurement = repo.allMeasurement()

    var measure by mutableStateOf(Measurement())
        private set

    //-------------------------------------------------------
    suspend fun getMeasurement(id: Long) {
        if (id == 0L) {
            measure = Measurement()
        } else {
            repo.getMeasurement(id).collect { measure = it }
        }
    }

    fun updateMSys(value: Int) {
        measure = measure.copy(morningSYS = value)
    }

    fun updateMDia(value: Int) {
        measure = measure.copy(morningDIA = value)
    }

    fun updateMPulse(value: Int) {
        measure = measure.copy(morningPulse = value)
    }

    fun updateESys(value: Int) {
        measure = measure.copy(eveningSYS = value)
    }

    fun updateEDia(value: Int) {
        measure = measure.copy(eveningDIA = value)
    }

    fun updateEPulse(value: Int) {
        measure = measure.copy(eveningPulse = value)
    }

    fun updateDate(value: Date) {
        measure = measure.copy(date = value)
    }
    //-------------------------------------------------------

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