package com.abudnitski.express.data.db.stationkeyword

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationKeywordEntity(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val keyword: String,
    @ColumnInfo val stationId: Int
)
