package com.abudnitski.express.data.db.stationkeyword

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationKeywordDao {
    @Query("SELECT * FROM stationkeywordentity")
    suspend fun getAll(): List<StationKeywordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stationKeyword: StationKeywordEntity)
}