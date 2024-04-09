package com.abudnitski.express.data

import android.content.Context
import android.content.SharedPreferences
import com.abudnitski.express.R
import com.abudnitski.express.data.api.ApiService
import com.abudnitski.express.data.db.AppDatabase
import com.abudnitski.express.data.db.station.StationDao
import com.abudnitski.express.data.db.stationkeyword.StationKeywordDao
import com.abudnitski.express.domain.list.StationKeywordMapper
import com.abudnitski.express.domain.main.StationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideStationRepo(
        stationDao: StationDao,
        stationKeywordDao: StationKeywordDao,
        stationMapper: StationMapper,
        stationKeywordMapper: StationKeywordMapper,
        apiService: ApiService,
        sharedPref: SharedPreferences
    ): StationRepository {
        return StationRepository(stationDao, stationKeywordDao, apiService, stationMapper, stationKeywordMapper, sharedPref)
    }

    @Provides
    fun provideStationDao(db: AppDatabase): StationDao {
        return db.stationDao()
    }

    @Provides
    fun provideStationKeywordDao(db: AppDatabase): StationKeywordDao {
        return db.stationKeywordDao()
    }

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.preferences_file), Context.MODE_PRIVATE
        )
    }

    @Provides
    fun provideDatabase(sharedPref: SharedPreferences, @ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context, sharedPref)
    }
}

const val BASE_URL = "https://koleo.pl/api/v2/main/"
