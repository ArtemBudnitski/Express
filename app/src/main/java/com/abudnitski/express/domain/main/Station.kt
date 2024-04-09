package com.abudnitski.express.domain.main

data class Station(
    val id: Int,
    val name: String,
    val nameSlug: String,
    val latitude: Float,
    val longitude: Float,
    val hits: Int,
    val ibnr: Int,
    val city: String,
    val region: String,
    val country: String,
    val localisedName: String?,
    val isGroup: Boolean,
    val hasAnnouncements: Boolean,
    val isNearbyStationEnabled: Boolean
)