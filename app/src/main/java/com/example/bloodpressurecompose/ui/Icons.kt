package com.example.bloodpressurecompose.ui

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.ui.theme.DayColor
import com.example.bloodpressurecompose.ui.theme.NightColor

@Composable
fun SunIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(R.drawable.ic_day),
        contentDescription = "Morning",
        modifier = modifier,
        tint = DayColor
    )
}

@Composable
fun NightIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(R.drawable.ic_night),
        contentDescription = "Evening",
        modifier = modifier,
        tint = NightColor
    )
}

@Composable
fun DeleteIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Delete,
        contentDescription = "delete",
        tint = MaterialTheme.colors.primary
    )
}