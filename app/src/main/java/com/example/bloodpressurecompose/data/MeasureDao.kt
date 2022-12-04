package com.example.bloodpressurecompose.data

import androidx.room.*
import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasureDao {
    @Query("select * from measurement order by date desc")
    fun getAll(): Flow<List<Measurement>>

    @Query("select * from measurement where id = :id")
    fun getMeasurement(id: Long): Flow<Measurement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Measurement)

    @Update
    suspend fun update(item: Measurement)

    @Delete
    suspend fun delete(item: Measurement)
}