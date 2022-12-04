package com.example.bloodpressurecompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Measurement(
    @PrimaryKey(autoGenerate = true)    val id: Long = 0,
    @ColumnInfo(name = "date")          var date: Date = Date(),
    @ColumnInfo(name = "m_sys")         var morningSYS: Int? = null,
    @ColumnInfo(name = "m_dia")         var morningDIA: Int? = null,
    @ColumnInfo(name = "m_pulse")       var morningPulse: Int? = null,
    @ColumnInfo(name = "e_sys")         var eveningSYS: Int? = null,
    @ColumnInfo(name = "e_dia")         var eveningDIA: Int? = null,
    @ColumnInfo(name = "e_pulse")       var eveningPulse: Int? = null,
)