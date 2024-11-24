package com.yash.apps.clockwise.presentation.reports

import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.presentation.reports.components.DateSelectorRow
import com.yash.apps.clockwise.presentation.reports.components.DayTasksPieChart
import com.yash.apps.clockwise.presentation.reports.components.MonthSelectorRow
import com.yash.apps.clockwise.util.Constants.FULL_DATE_FORMAT
import com.yash.apps.clockwise.util.Constants.ONLY_MONTH_YEAR_FORMAT
import com.yash.apps.clockwise.util.Constants.REPORT_SHARE_IMAGE_NAME
import com.yash.apps.clockwise.util.DateFormatter
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import java.io.File

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
    ExperimentalComposeApi::class
)
@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier,
    viewModel: ReportsViewModel,
    bottomBarContent: @Composable () -> Unit
) {
    val context = LocalContext.current
    val captureController = rememberCaptureController()
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.reportScreenUiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Reports", style = MaterialTheme.typography.headlineLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                val bitmapAsync = captureController.captureAsync()
                                try {
                                    val bitmap = bitmapAsync.await()
                                    val file = File(
                                        context.cacheDir,
                                        "Report " + DateFormatter.formatDate(
                                            uiState.dateSelected,
                                            REPORT_SHARE_IMAGE_NAME
                                        ) + ".jpg"
                                    )
                                    file.outputStream().use { out ->
                                        bitmap.asAndroidBitmap().compress(
                                            Bitmap.CompressFormat.JPEG,
                                            100,
                                            out
                                        )
                                    }
                                    val uri = FileProvider.getUriForFile(
                                        context,
                                        "${context.packageName}.provider",
                                        file
                                    )
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "image/jpeg"
                                        putExtra(Intent.EXTRA_STREAM, uri)
                                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                    }
                                    context.startActivity(
                                        Intent.createChooser(
                                            shareIntent,
                                            "Share Image"
                                        )
                                    )
                                } catch (error: Throwable) {
                                    Toast.makeText(
                                        context,
                                        "Some error in sharing image",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        enabled = uiState.reportDataList.isNotEmpty()
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = "Share the report",
                        )
                    }
                }
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
            MonthSelectorRow(
                month = DateFormatter.formatDate(uiState.selectedMonth, ONLY_MONTH_YEAR_FORMAT),
                onPrevClick = viewModel::gotoPrevMonth,
                onNextClick = viewModel::gotoNextMonth,
                isNextEnabled = viewModel.isNextEnabled
            )
            Spacer(modifier = Modifier.height(4.dp))
            DateSelectorRow(
                dates = uiState.monthDates,
                dateSelected = uiState.dateSelected,
                todayDate = uiState.todayDate,
                onDateSelected = viewModel::updateSelectedDate,
                lazyListState = viewModel.dateSelectorScrollState
            )
            Spacer(modifier = Modifier.height(32.dp))
            DayTasksPieChart(
                modifier = Modifier.capturable(captureController),
                dateSelected = DateFormatter.formatDate(uiState.dateSelected, FULL_DATE_FORMAT),
                reportDataList = uiState.reportDataList,
            )
        }
    }
}