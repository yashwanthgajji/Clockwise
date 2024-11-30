package com.yash.apps.clockwise.presentation.newrecord.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.SubTask

@Composable
fun RecordSubTaskSelector(
    modifier: Modifier = Modifier,
    items: List<SubTask>,
    selectedItem: SubTask?,
    onItemSelected: (SubTask?) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .pointerInput(selectedItem) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            expanded = true
                        }
                    }
                },
            value = selectedItem?.sName?: "Select a Sub Task",
            label = { Text(text = "Select Sub Task", style = MaterialTheme.typography.labelMedium) },
            placeholder = { Text(text = "Select a SubTask") },
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.task_box_icon),
                    contentDescription = null
                )
            }
        )
        DropdownMenu(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            scrollState = rememberScrollState()
        ) {
            DropdownMenuItem(
                text = { Text(text = "Select a SubTask", style = MaterialTheme.typography.labelMedium) },
                onClick = {
                    onItemSelected(null)
                    expanded = false
                }
            )
            HorizontalDivider(thickness = 2.dp)
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.sName, style = MaterialTheme.typography.labelMedium) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}