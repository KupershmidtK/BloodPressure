package com.example.bloodpressurecompose.ui

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.*

fun showDatePicker(
    context : Context,
    date: Date,
    updatedDate: (Date) -> Unit)
{
    val calendar = Calendar.getInstance()
    calendar.time = date

    val picker = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            calendar.set(mYear, mMonth, mDayOfMonth)
            updatedDate(calendar.time)
        },
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH],
        calendar[Calendar.DAY_OF_MONTH]
    )

    picker.show()
}