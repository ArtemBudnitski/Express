package com.abudnitski.express.data.api


@Suppress("PropertyName")
data class StationNet(
    val id: Int,
    val name: String,
    val name_slug: String,
    val latitude: Float,
    val longitude: Float,
    val hits: Int,
    val ibnr: Int,
    val city: String,
    val region: String,
    val country: String,
    val localised_name: String?,
    val is_group: Boolean,
    val has_announcements: Boolean,
    val is_nearby_station_enabled: Boolean
)
