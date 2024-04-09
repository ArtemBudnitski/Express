package com.abudnitski.express

import android.content.SharedPreferences
import com.abudnitski.express.data.StationRepository
import com.abudnitski.express.data.api.ApiService
import com.abudnitski.express.data.db.station.StationDao
import com.abudnitski.express.data.db.station.StationEntity
import com.abudnitski.express.data.db.stationkeyword.StationKeywordDao
import com.abudnitski.express.domain.list.StationKeywordMapper
import com.abudnitski.express.domain.main.Station
import com.abudnitski.express.domain.main.StationMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StationRepositoryTest {

    private val stationDao: StationDao = mockk()
    private val stationKeywordDao: StationKeywordDao = mockk()
    private val apiService: ApiService = mockk()
    private val stationMapper: StationMapper = mockk()
    private val stationKeywordMapper: StationKeywordMapper = mockk()
    private val sharedPref: SharedPreferences = mockk()

    private val sut = StationRepository(
        stationDao,
        stationKeywordDao,
        apiService,
        stationMapper,
        stationKeywordMapper,
        sharedPref
    )

    @Test
    fun `Given station id When find station Then return Station`() {
        runTest {
            val id = "id"
            val station = mockk<Station>()
            val stationEntity = mockk<StationEntity>()
            coEvery { stationDao.findByCode(id) } returns stationEntity
            coEvery { stationMapper.map(stationEntity) } returns station

            val result = sut.findStations(id)

            assertEquals(station, result)
            coVerify(exactly = 1) { stationDao.findByCode(id) }
            coVerify(exactly = 1) { stationMapper.map(stationEntity) }
        }
    }
}