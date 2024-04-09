package com.abudnitski.express.domain

import com.abudnitski.express.domain.list.StationKeywordMapper
import com.abudnitski.express.domain.main.StationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideStationMapper(): StationMapper {
        return StationMapper()
    }

    @Provides
    fun provideStationKeywordMapper(): StationKeywordMapper {
        return StationKeywordMapper()
    }
}