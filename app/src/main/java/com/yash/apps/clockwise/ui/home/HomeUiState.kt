package com.yash.apps.clockwise.ui.home

import com.yash.apps.clockwise.model.Record

data class HomeUiState(
    val records: List<Record>,
    val sections: List<Section>
)
