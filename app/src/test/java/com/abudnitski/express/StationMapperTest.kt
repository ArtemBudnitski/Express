package com.abudnitski.express.domain.main

import com.abudnitski.express.data.api.StationNet
import com.abudnitski.express.data.db.station.StationEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class StationMapperTest {
    private val stationMapper = StationMapper()

    @Test
    fun `map entity to station`() {
        val data = listOf(
            StationEntity(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            StationEntity(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val expected = listOf(
            Station(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            Station(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val result = stationMapper.mapEntity(data)

        assertEquals(expected, result)
    }

    @Test
    fun `map station to entity`() {
        val data = listOf(
            Station(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            Station(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val expected = listOf(
            StationEntity(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            StationEntity(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val result = stationMapper.map(data)

        assertEquals(expected, result)
    }

    @Test
    fun `map net to station`() {
        val data = listOf(
            StationNet(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            StationNet(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val expected = listOf(
            Station(
                1,
                "Station1",
                "station1",
                50.0f,
                20.0f,
                100,
                1,
                "City1",
                "Region1",
                "Country1",
                "LocalisedName1",
                true,
                true,
                true
            ),
            Station(
                2,
                "Station2",
                "station2",
                51.0f,
                21.0f,
                200,
                2,
                "City2",
                "Region2",
                "Country2",
                "LocalisedName2",
                false,
                false,
                false
            )
        )

        val result = stationMapper.mapNet(data)

        assertEquals(expected, result)
    }
}
