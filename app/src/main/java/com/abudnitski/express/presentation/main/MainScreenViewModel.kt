package com.abudnitski.express.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abudnitski.express.data.StationRepository
import com.abudnitski.express.domain.main.Station
import com.abudnitski.express.ui.STATION_FIELD_KEY
import com.abudnitski.express.ui.STATION_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repo: StationRepository,
    private val saveState: SavedStateHandle,
    private val distanceHelper: DistanceHelper
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(isDataLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                init()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value =
                        _uiState.value.copy(
                            isDataError = true,
                            isDataLoading = false,
                            tryAgainButton = { onTryAgain() })
                }
            }
        }
    }

    private suspend fun init() {
        val stationField = saveState.get<String>(STATION_FIELD_KEY)
        val stationId = saveState.get<String>(STATION_ID_KEY)
        if (stationId != null) {
            if (stationField?.toInt() == 1) {
                repo.startStationId = stationId
            } else {
                repo.endStationId = stationId
            }
        }

        var startPoint: Station? = null
        var endPoint: Station? = null

        if (repo.startStationId.isNotBlank()) {
            startPoint = repo.findStations(repo.startStationId)
        }

        if (repo.endStationId.isNotBlank()) {
            endPoint = repo.findStations(repo.endStationId)
        }

        setInitUiState(startPoint, endPoint)
    }

    private suspend fun setInitUiState(startPoint: Station?, endPoint: Station?) {

        val isButtonEnabled = startPoint != endPoint && startPoint != null && endPoint != null

        withContext(Dispatchers.Main) {
            _uiState.value = _uiState.value.copy(
                dateTitle = currentDateFormatted(),
                startPoint = startPoint,
                endPoint = endPoint,
                startSubtitle = formatSubtitle(startPoint),
                endSubtitle = formatSubtitle(endPoint),
                isSearchEnabled = isButtonEnabled,
                isDataLoading = false,
                isDataError = false,
                onSearchClick = { onSearchClick() },
                showDistanceCard = false
            )
        }
    }

    private fun onSearchClick() {
        with(_uiState.value) {
            startPoint?.let { startStation ->
                endPoint?.let { endStation ->
                    val distance = distanceHelper.calculateDistance(startStation, endStation)
                    _uiState.value = copy(showDistanceCard = true, distance = "%.2f".format(Locale.US, distance))
                }
            }
        }
    }

    private fun onTryAgain() {
        loadData()
    }

    private fun currentDateFormatted(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    private fun formatSubtitle(station: Station?): String {
        var text = ""

        station?.let {
            if (station.city.isNotBlank()) {
                text = text + station.city + ", "
            }

            if (station.region.isNotBlank()) {
                text = text + station.region + ", "
            }

            if (station.country.isNotBlank()) {
                text += station.country
            }

            if (text.endsWith(", ")) {
                text = text.dropLast(2)
            }
        }

        return text
    }
}