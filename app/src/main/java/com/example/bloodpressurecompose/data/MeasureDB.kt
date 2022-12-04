package com.example.bloodpressurecompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bloodpressurecompose.model.Converters
import com.example.bloodpressurecompose.model.Measurement


@Database(entities = [Measurement::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MeasureDB : RoomDatabase() {
    abstract fun measureDao(): MeasureDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MeasureDB? = null
//
//        fun getDatabase(context: Context): MeasureDB {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MeasureDB::class.java,
//                    "blood_pressure_db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}