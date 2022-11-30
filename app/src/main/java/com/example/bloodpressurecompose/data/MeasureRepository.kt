package com.example.bloodpressurecompose.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlin.coroutines.coroutineContext

class MeasureRepository(private val measureDao: MeasurementDao) {
    val allMeasurement = measureDao.getAll()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertMeasure(measure: Measurement) {
        coroutineScope.launch {
            measureDao.insert(measure)
        }
    }

    fun updateMeasure(measure: Measurement) {
        coroutineScope.launch {
            measureDao.update(measure)
        }
    }

    fun deleteMeasure(measure: Measurement) {
        coroutineScope.launch {
            measureDao.delete(measure)
        }
    }

    fun getMeasurement(id: Long) = runBlocking(Dispatchers.IO) {
//            delay(5000)
            measureDao.getMeasurement(id).first()
    }
}