package com.example.bloodpressurecompose.data

import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.flow.Flow

interface IMeasureRepository {
    fun allMeasurement(): Flow<List<Measurement>>
    fun getMeasurement(id: Long): Flow<Measurement>
    suspend fun insertMeasure(measure: Measurement)
    suspend fun updateMeasure(measure: Measurement)
    suspend fun deleteMeasure(measure: Measurement)
}