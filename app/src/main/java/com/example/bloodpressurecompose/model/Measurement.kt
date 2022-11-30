package com.example.bloodpressurecompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Measurement(
    @PrimaryKey(autoGenerate = true)    val id: Long = 0,
    @ColumnInfo(name = "date")          val date: Date,
    @ColumnInfo(name = "m_sys")         val morningSYS: Int?,
    @ColumnInfo(name = "m_dia")         val morningDIA: Int?,
    @ColumnInfo(name = "m_pulse")       val morningPulse: Int?,
    @ColumnInfo(name = "e_sys")         val eveningSYS: Int?,
    @ColumnInfo(name = "e_dia")         val eveningDIA: Int?,
    @ColumnInfo(name = "e_pulse")       val eveningPulse: Int?,
)