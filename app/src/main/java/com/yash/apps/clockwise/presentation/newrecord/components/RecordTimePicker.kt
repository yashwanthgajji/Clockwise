package com.yash.apps.clockwise.presentation.newrecord.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.util.Constants.TIME_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordTimePicker(
    modifier: Modifier = Modifier,
    label: String,
    selectedTime: Calendar,
    onTimeChange: (Calendar) -> Unit
) {
    var showTimePicker by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier
            .padding(vertical = 8.dp)
            .pointerInput(selectedTime) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showTimePicker = true
                    }
                }
            },
        value = DateFormatter.formatDate(selectedTime.time, TIME_FORMAT),
        label = { Text(text = label) },
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.clock_icon),
                contentDescription = null
            )
        }
    )
    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = selectedTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = selectedTime.get(Calendar.MINUTE),
            is24Hour = false
        )
        TimePickerDialog(
            onDismiss = { showTimePicker = false },
            onConfirm = {
                onTimeChange(convertTimePickerToDate(selectedTime, timePickerState))
                showTimePicker = false
            }) {
            TimePicker(state = timePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun convertTimePickerToDate(calendar: Calendar, timePickerState: TimePickerState): Calendar {
    val cal = calendar.clone() as Calendar
    cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    cal.set(Calendar.MINUTE, timePickerState.minute)
    return cal
}

@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Done")
            }
        },
        text = { content() }
    )
}