package com.abudnitski.express.presentation.list

import com.abudnitski.express.domain.list.StationKeyword
import com.abudnitski.express.domain.main.Station


data class ListScreenUiState(
    val stationKeywords: List<StationKeyword>,
    val stations: List<Station>,
    val filteredStations: List<Station>,
    val searchText: String,
    val onSearch: (String) -> Unit,
    val isDataError: Boolean,
    val tryAgainButton: () -> Unit,
    val isDataLoading: Boolean
)
