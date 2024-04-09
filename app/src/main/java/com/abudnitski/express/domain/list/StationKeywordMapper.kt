package com.abudnitski.express.domain.list

import com.abudnitski.express.data.api.StationKeywordNet
import com.abudnitski.express.data.db.stationkeyword.StationKeywordEntity

class StationKeywordMapper {
    fun mapEntity(data: List<StationKeywordEntity>): List<StationKeyword> {
        return data.map { map(it) }
    }

    private fun map(data: StationKeywordEntity): StationKeyword {
        return StationKeyword(
            id = data.id,
            keyword = data.keyword,
            stationId = data.stationId
        )
    }

    fun map(data: List<StationKeyword>): List<StationKeywordEntity> {
        return data.map { map(it) }
    }

    private fun map(data: StationKeyword): StationKeywordEntity {
        return StationKeywordEntity(
            id = data.id,
            keyword = data.keyword,
            stationId = data.stationId
        )
    }

    fun mapNet(data: List<StationKeywordNet>): List<StationKeyword> {
        return data.map { mapNet(it) }
    }

    private fun mapNet(data: StationKeywordNet): StationKeyword {
        return StationKeyword(
            id = data.id,
            keyword = data.keyword,
            stationId = data.station_id
        )
    }
}