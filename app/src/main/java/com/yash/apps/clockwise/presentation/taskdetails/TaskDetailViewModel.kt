package com.yash.apps.clockwise.presentation.taskdetails

import androidx.lifecycle.ViewModel
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {

}