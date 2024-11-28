package com.yash.apps.clockwise.presentation.newrecord

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.presentation.newrecord.components.NewRecordTopAppBar
import com.yash.apps.clockwise.presentation.newrecord.components.RecordDatePicker
import com.yash.apps.clockwise.presentation.newrecord.components.RecordSubTaskSelector
import com.yash.apps.clockwise.presentation.newrecord.components.RecordTimePicker
import com.yash.apps.clockwise.util.Constants.DATE_FORMAT
import com.yash.apps.clockwise.util.DateFormatter

@Composable
fun NewRecordScreen(
    modifier: Modifier = Modifier,
    viewModel: NewRecordViewModel,
    onBackPress: () -> Unit
) {
    val uiState by viewModel.newRecordUiState.collectAsState()
    val isAllFilled = uiState.let {
        it.task != null && it.startTime < it.endTime
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NewRecordTopAppBar(onBackPress = onBackPress) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .padding(innerPadding)
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            uiState.let {
                if (!viewModel.isSubTaskRecord && !it.subTasks.isNullOrEmpty()) {
                    RecordSubTaskSelector(
                        items = it.subTasks,
                        selectedItem = uiState.subTask,
                        onItemSelected = viewModel::onSubTaskChange
                    )
                    HorizontalDivider()
                }
            }
            RecordDatePicker(
                modifier = Modifier.fillMaxWidth(),
                selectedDate = DateFormatter.formatDate(uiState.date, DATE_FORMAT),
                onDateChange = viewModel::onDateChange
            )
            HorizontalDivider()
            RecordTimePicker(
                modifier = Modifier.fillMaxWidth(),
                label = "Start Time",
                selectedTime = uiState.startTime,
                onTimeChange = viewModel::onStartTimeChange
            )
            HorizontalDivider()
            RecordTimePicker(
                modifier = Modifier.fillMaxWidth(),
                label = "End Time",
                selectedTime = uiState.endTime,
                onTimeChange = viewModel::onEndTimeChange
            )
            HorizontalDivider()
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = isAllFilled,
                onClick = {
                    val saved = viewModel.saveRecord()
                    if (saved) {
                        onBackPress()
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}