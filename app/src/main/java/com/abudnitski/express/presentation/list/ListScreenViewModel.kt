package com.abudnitski.express.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abudnitski.express.data.StationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: StationRepository,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ListScreenUiState(
                emptyList(),
                searchText = "",
                onSearch = ::setSearchText,
                filteredStations = emptyList(),
                stations = emptyList(),
                tryAgainButton = { tryAgain() },
                isDataError = false,
                isDataLoading = true
            )
        )
    val uiState: StateFlow<ListScreenUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(isDataLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val keywords = repository.getAllStationKeywords()
                val stations = repository.getAllStations()

                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(
                        stationKeywords = keywords,
                        filteredStations = stations,
                        stations = stations,
                        isDataError = false,
                        isDataLoading = false
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(
                        isDataError = true,
                        isDataLoading = false
                    )
                }
            }
        }
    }

    private fun tryAgain() {
        loadData()
    }

    fun setSearchText(text: String) {
        val filteredKeywords = _uiState.value.stationKeywords.filter { it.keyword.startsWith(text, true) }
        val stationIds = filteredKeywords.map { it.stationId }
        val filteredStations = _uiState.value.stations.filter { stationIds.contains(it.id) }
        val sortedStations = filteredStations.sortedByDescending { it.hits }
        _uiState.value = _uiState.value.copy(
            searchText = text,
            filteredStations = sortedStations
        )
    }
}
