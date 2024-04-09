package com.abudnitski.express.data.api

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("stations")
    @Headers("X-KOLEO-Version: 1")
    suspend fun getAllStations(): List<StationNet>

    @GET("station_keywords")
    @Headers("X-KOLEO-Version: 1")
    suspend fun getAllStationKeywords(): List<StationKeywordNet>
}
