package com.abudnitski.express.data

import android.content.SharedPreferences
import com.abudnitski.express.data.api.ApiService
import com.abudnitski.express.data.db.station.StationDao
import com.abudnitski.express.data.db.stationkeyword.StationKeywordDao
import com.abudnitski.express.domain.list.StationKeyword
import com.abudnitski.express.domain.list.StationKeywordMapper
import com.abudnitski.express.domain.main.Station
import com.abudnitski.express.domain.main.StationMapper
import java.time.LocalDate
import java.time.ZoneOffset

class StationRepository(
    private val stationDao: StationDao,
    private val stationKeywordDao: StationKeywordDao,
    private val apiService: ApiService,
    private val stationMapper: StationMapper,
    private val stationKeywordMapper: StationKeywordMapper,
    private val sharedPref: SharedPreferences
) {

    var startStationId: String = ""
    var endStationId: String = ""

    suspend fun findStations(id: String): Station {
        val data = stationDao.findByCode(id = id)
        return stationMapper.map(data)
    }

    suspend fun getAllStations(): List<Station> {
        return if (useCache(LAST_STATION_UPDATE_KEY)) {
            stationMapper.mapEntity(stationDao.getAll())
        } else {
            val netModels = apiService.getAllStations()
            val domainModels = stationMapper.mapNet(netModels)
            updateCache(LAST_STATION_UPDATE_KEY)
            insertStations(domainModels)
            domainModels
        }
    }

    suspend fun getAllStationKeywords(): List<StationKeyword> {
        return if (useCache(LAST_STATION_KEYWORD_UPDATE_KEY)) {
            stationKeywordMapper.mapEntity(stationKeywordDao.getAll())
        } else {
            val netModels = apiService.getAllStationKeywords()
            val domainModels = stationKeywordMapper.mapNet(netModels)
            updateCache(LAST_STATION_KEYWORD_UPDATE_KEY)
            insertStationsKeywords(domainModels)
            domainModels
        }
    }

    private suspend fun insertStations(data: List<Station>) {
        val entityModels = stationMapper.map(data)
        stationDao.insertAll(*entityModels.toTypedArray())
    }

    private suspend fun insertStationsKeywords(data: List<StationKeyword>) {
        val entityModels = stationKeywordMapper.map(data)
        stationKeywordDao.insertAll(*entityModels.toTypedArray())
    }

    private fun useCache(key: String): Boolean {
        val lastUpdate = sharedPref.getLong(key, 0)
        return lastUpdate == getTodayDate()
    }

    private fun updateCache(key: String) {
        val today = getTodayDate()
        with(sharedPref.edit()) {
            putLong(key, today)
            apply()
        }
    }

    private fun getTodayDate() = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

    companion object {
        const val LAST_STATION_UPDATE_KEY = "LAST_STATION_UPDATE_KEY"
        const val LAST_STATION_KEYWORD_UPDATE_KEY = "LAST_STATION_KEYWORD_UPDATE_KEY"
    }
}
