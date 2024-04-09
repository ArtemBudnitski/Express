package com.abudnitski.express.data.db

import com.abudnitski.express.data.db.station.StationDao
import com.abudnitski.express.data.db.station.StationEntity
import com.abudnitski.express.data.db.stationkeyword.StationKeywordDao
import com.abudnitski.express.data.db.stationkeyword.StationKeywordEntity
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abudnitski.express.data.StationRepository.Companion.LAST_STATION_KEYWORD_UPDATE_KEY
import com.abudnitski.express.data.StationRepository.Companion.LAST_STATION_UPDATE_KEY


@Database(entities = [StationEntity::class, StationKeywordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
    abstract fun stationKeywordDao(): StationKeywordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            sharedPref: SharedPreferences
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "express_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDataBase(sharedPref))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDataBase(
            private val sharedPref: SharedPreferences
        ) : Callback() {
            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                with(sharedPref.edit()) {
                    putLong(LAST_STATION_UPDATE_KEY, 0)
                    putLong(LAST_STATION_KEYWORD_UPDATE_KEY, 0)
                    apply()
                }
            }
        }
    }
}
