package com.abudnitski.express.data.db.station

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationDao {
    @Query("SELECT * FROM stationentity")
    suspend fun getAll(): List<StationEntity>

    @Query("SELECT * FROM stationentity WHERE id = :id")
    suspend fun findByCode(id: String): StationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg stations: StationEntity)
}