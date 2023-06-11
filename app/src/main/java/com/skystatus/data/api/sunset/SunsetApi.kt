package com.skystatus.data.api.sunset

import com.skystatus.data.api.sunset.model.SunsetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SunsetApi {

    @GET("/v1/quality")
    suspend fun getQualitySunset(
        @Query("geo") geo: String,
    ): SunsetResponse

}