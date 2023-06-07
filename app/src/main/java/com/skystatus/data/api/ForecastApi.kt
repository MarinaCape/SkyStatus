package com.skystatus.data.api

import com.skystatus.data.api.model.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("/locations/v1/cities/autocomplete")
    suspend fun getCitiesByText(
        @Query("q") search: String,
    ): List<CityResponse>
}