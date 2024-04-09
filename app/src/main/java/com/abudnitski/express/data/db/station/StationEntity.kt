package com.abudnitski.express.data.db.station

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationEntity(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val nameSlug: String,
    @ColumnInfo val latitude: Float,
    @ColumnInfo val longitude: Float,
    @ColumnInfo val hits: Int,
    @ColumnInfo val ibnr: Int,
    @ColumnInfo val city: String,
    @ColumnInfo val region: String,
    @ColumnInfo val country: String,
    @ColumnInfo val localisedName: String?,
    @ColumnInfo val isGroup: Boolean,
    @ColumnInfo val hasAnnouncements: Boolean,
    @ColumnInfo val isNearbyStationEnabled: Boolean
)
