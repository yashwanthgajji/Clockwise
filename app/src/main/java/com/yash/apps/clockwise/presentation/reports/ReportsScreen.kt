package com.yash.apps.clockwise.presentation.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.presentation.reports.components.DateSelectorRow
import com.yash.apps.clockwise.presentation.reports.components.DayTasksPieChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier,
    viewModel: ReportsViewModel,
    bottomBarContent: @Composable () -> Unit
) {
    val uiState by viewModel.reportScreenUiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Reports") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        },
        bottomBar = bottomBarContent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateSelectorRow(
                dates = uiState.monthDates,
                dateSelected = uiState.dateSelected,
                todayDate = uiState.todayDate,
                onDateSelected = viewModel::updateSelectedDate
            )
            Spacer(modifier = Modifier.height(32.dp))
            DayTasksPieChart(
                modifier = Modifier,
                reportDataList = uiState.reportDataList,
            )
        }
    }
}