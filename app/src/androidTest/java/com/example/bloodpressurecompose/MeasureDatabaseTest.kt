package com.example.bloodpressurecompose

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bloodpressurecompose.data.MeasurementDao
import com.example.bloodpressurecompose.data.MeasurementDatabase
import com.example.bloodpressurecompose.model.Measurement
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class MeasureDatabaseTest {
    private lateinit var measureDao: MeasurementDao
    private lateinit var db: MeasurementDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MeasurementDatabase::class.java)
            .allowMainThreadQueries()
            .build()

//        db = MeasurementDatabase.getDatabase(context)
        measureDao = db.measurementDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDB() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetMeasure() {
        runBlocking {
            val expectedId = 5L;
            val measurement = Measurement (
                date = Date(),
                morningSYS = 120,
                morningDIA = 60,
                morningPulse = 70,
                eveningSYS = 120,
                eveningDIA = 60,
                eveningPulse = 70 )

            measureDao.insert(measurement)

            val savedMeasure = measureDao.getMeasurement(1).first()
//            val all = measureDao.getAll()
//            val savedMeasure = all.first()
            assertEquals(1L, savedMeasure.id)
        }
    }
}