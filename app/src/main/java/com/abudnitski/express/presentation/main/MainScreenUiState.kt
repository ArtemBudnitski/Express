package com.abudnitski.express.presentation.main

import com.abudnitski.express.domain.main.Station

data class MainScreenUiState(
    val dateTitle : String = "",
    val startPoint : Station? = null,
    val endPoint: Station? = null,
    val startSubtitle: String = "",
    val endSubtitle: String = "",
    val isSearchEnabled: Boolean = false,
    val onSearchClick: () -> Unit = {},
    val isDataLoading: Boolean = true,
    val isDataError: Boolean = false,
    val tryAgainButton : () -> Unit = {},
    val showDistanceCard : Boolean = false,
    val distance: String = ""
)
