package com.example.bloodpressurecompose.data

import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first

class MeasureRepositoryImpl(private val measureDao: MeasureDao) : IMeasureRepository {
    override fun allMeasurement() = measureDao.getAll()

    override fun getMeasurement(id: Long) = measureDao.getMeasurement(id)

    override suspend fun insertMeasure(measure: Measurement) =  measureDao.insert(measure)

    override suspend fun updateMeasure(measure: Measurement) = measureDao.update(measure)

    override suspend fun deleteMeasure(measure: Measurement) = measureDao.delete(measure)
}