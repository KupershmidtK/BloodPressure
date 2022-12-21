package com.example.bloodpressurecompose.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.bloodpressurecompose.R
import com.example.bloodpressurecompose.model.Measurement
import com.example.bloodpressurecompose.ui.theme.DayColor
import com.example.bloodpressurecompose.ui.theme.NightColor
import com.example.bloodpressurecompose.ui.theme.Shapes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import kotlin.math.absoluteValue

@Composable
fun MeasureValue(@StringRes label: Int, value: Int?) {
    Row(
        modifier = Modifier
            .padding(end = 16.dp),
    ) {
        Text(
            text = stringResource(label),
        )
        Text(
            text = value?.toString() ?: "-",
            modifier = Modifier
                .width(32.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primaryVariant)
    }

}

@SuppressLint("ReturnFromAwaitPointerEventScope", "MultipleAwaitPointerEventScopes")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MeasureItem(
    modifier: Modifier = Modifier,
    measurement: Measurement,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit) {

    val offsetX = remember { Animatable(0f) }

    Box(
        contentAlignment = Alignment.CenterStart,

        modifier = modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .fillMaxWidth()

        .pointerInput(Unit) {
            val offset = size.width.toFloat() / 3
            offsetX.updateBounds(
                lowerBound = -offset,
                upperBound = 0f
            )

            coroutineScope {
                while (true) {
                    val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                    offsetX.stop()

                    awaitPointerEventScope {
                        horizontalDrag(pointerId) { change ->
                            launch {
                                offsetX.snapTo(offsetX.value + change.positionChange().x)
                            }
                        }
                    }

                    launch {
                        if(offsetX.value.absoluteValue <= offset / 2) {
                            offsetX.animateTo(
                                targetValue = offsetX.upperBound!!,
                                animationSpec = tween(
                                    easing = LinearEasing
                                )
                            )
                        } else {
                            offsetX.animateTo(
                                targetValue = offsetX.lowerBound!!,
                                animationSpec = tween(
                                    easing = LinearEasing
                                )
                            )
                        }
                    }
                }
            }
        },
    ) {
        // background
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = onClick,
                Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "edit", tint = NightColor)
            }

            IconButton(
                onClick = onDeleteClick,
                Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete", tint = DayColor)
            }
        }

        // frontend
        Card(
            modifier = modifier
                .offset { IntOffset(offsetX.value.toInt(), 0) },
            shape = Shapes.medium,
            elevation = 4.dp
        ) {
            Column(modifier = modifier.padding(4.dp)) {
                Text(
                    text = DateFormat.getDateInstance().format(measurement.date),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant
                )

                Row {
                    Column {
                        Row(modifier.padding(4.dp)) {
                            SunIcon(modifier.padding(start = 4.dp, end = 16.dp))
                            MeasureValue(label = R.string.sys, value = measurement.morningSYS)
                            MeasureValue(label = R.string.dia, value = measurement.morningDIA)
                            MeasureValue(label = R.string.pulse, value = measurement.morningPulse)
                        }
                        Row(modifier.padding(4.dp)) {
                            NightIcon(modifier.padding(start = 4.dp, end = 16.dp))
                            MeasureValue(label = R.string.sys, value = measurement.eveningSYS)
                            MeasureValue(label = R.string.dia, value = measurement.eveningDIA)
                            MeasureValue(label = R.string.pulse, value = measurement.eveningPulse)
                        }
                    }
                }
            }
        }
    }
}