package com.example.bloodpressurecompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bloodpressurecompose.data.MeasureRepository
import com.example.bloodpressurecompose.data.MeasurementDatabase
import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.flow.Flow
import java.util.*

class MeasureViewModel(application: Application) : ViewModel() {
    val allMeasurement: Flow<List<Measurement>>
    private val repository: MeasureRepository

    init {
        val measureDB = MeasurementDatabase.getDatabase(application)
        val measureDao = measureDB.measurementDao()

        repository = MeasureRepository(measureDao)
        allMeasurement = repository.allMeasurement
    }

    fun getMeasurement(id: Long) = repository.getMeasurement(id)

    fun addMeasurement(
        date: Date,
        morningSys: Int?, morningDia: Int?, morningPulse: Int?,
        eveningSys: Int?, eveningDia: Int?, eveningPulse: Int?,
    ) {
        val measurement = Measurement (
            date = date,
            morningSYS = morningSys, morningDIA = morningDia, morningPulse = morningPulse,
            eveningSYS = eveningSys, eveningDIA = eveningDia, eveningPulse = eveningPulse,
        )
        repository.insertMeasure(measurement)
    }

    fun updateMeasurement(
        id: Long, date: Date,
        morningSys: Int?, morningDia: Int?, morningPulse: Int?,
        eveningSys: Int?, eveningDia: Int?, eveningPulse: Int?,
    ) {
        val measurement = Measurement (
            id, date,
            morningSys, morningDia, morningPulse,
            eveningSys, eveningDia, eveningPulse,
        )

        repository.updateMeasure(measurement)
    }

    fun deleteMeasurement(measurement: Measurement) {
        repository.deleteMeasure(measurement)
    }
}

@Suppress("UNCHECKED_CAST")
class MeasureViewModelFactory(private val application: Application):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeasureViewModel::class.java)) {
            return MeasureViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}