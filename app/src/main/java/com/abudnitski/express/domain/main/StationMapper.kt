package com.abudnitski.express.domain.main

import com.abudnitski.express.data.api.StationNet
import com.abudnitski.express.data.db.station.StationEntity

class StationMapper {

    fun mapEntity(data: List<StationEntity>): List<Station> {
        return data.map { map(it) }
    }

    fun map(data: StationEntity): Station {
        return Station(
            id = data.id,
            name = data.name,
            nameSlug = data.nameSlug,
            latitude = data.latitude,
            longitude = data.longitude,
            hits = data.hits,
            ibnr = data.ibnr,
            city = data.city,
            region = data.region,
            country = data.country,
            localisedName = data.localisedName,
            isGroup = data.isGroup,
            hasAnnouncements = data.hasAnnouncements,
            isNearbyStationEnabled = data.isNearbyStationEnabled
        )
    }

    fun map(data: List<Station>): List<StationEntity> {
        return data.map { map(it) }
    }

    private fun map(data: Station): StationEntity {
        return StationEntity(
            id = data.id,
            name = data.name,
            nameSlug = data.nameSlug,
            latitude = data.latitude,
            longitude = data.longitude,
            hits = data.hits,
            ibnr = data.ibnr,
            city = data.city,
            region = data.region,
            country = data.country,
            localisedName = data.localisedName,
            isGroup = data.isGroup,
            hasAnnouncements = data.hasAnnouncements,
            isNearbyStationEnabled = data.isNearbyStationEnabled
        )
    }

    fun mapNet(data: List<StationNet>): List<Station> {
        return data.map { mapNet(it) }
    }

    private fun mapNet(data: StationNet): Station {
        return Station(
            id = data.id,
            name = data.name,
            nameSlug = data.name_slug,
            latitude = data.latitude,
            longitude = data.longitude,
            hits = data.hits,
            ibnr = data.ibnr,
            city = data.city,
            region = data.region,
            country = data.country,
            localisedName = data.localised_name,
            isGroup = data.is_group,
            hasAnnouncements = data.has_announcements,
            isNearbyStationEnabled = data.is_nearby_station_enabled
        )
    }
}