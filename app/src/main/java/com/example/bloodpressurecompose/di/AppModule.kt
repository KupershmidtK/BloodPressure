package com.example.bloodpressurecompose.di

import android.content.Context
import androidx.room.Room
import com.example.bloodpressurecompose.data.IMeasureRepository
import com.example.bloodpressurecompose.data.MeasureDB
import com.example.bloodpressurecompose.data.MeasureDao
import com.example.bloodpressurecompose.data.MeasureRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providesMeasureDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(
                context,
                MeasureDB::class.java,
                "blood_pressure_db")
            //.fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesMeasureDao(measureDB: MeasureDB) = measureDB.measureDao()

    @Provides
    fun provideMeasureRepo(measureDao: MeasureDao): IMeasureRepository =
        MeasureRepositoryImpl(measureDao = measureDao)
}