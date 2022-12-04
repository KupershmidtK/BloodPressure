package com.example.bloodpressurecompose

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bloodpressurecompose.data.MeasureDB
import com.example.bloodpressurecompose.data.MeasureDao
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
    private lateinit var measureDao: MeasureDao
    private lateinit var db: MeasureDB

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MeasureDB::class.java)
            .allowMainThreadQueries()
            .build()

//        db = MeasurementDatabase.getDatabase(context)
        measureDao = db.measureDao()
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