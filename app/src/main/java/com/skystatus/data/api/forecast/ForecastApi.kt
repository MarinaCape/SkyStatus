package com.skystatus.data.api.forecast

import com.skystatus.data.api.forecast.model.CityResponse
import com.skystatus.data.api.forecast.model.daily.ForecastResponse
import com.skystatus.data.api.forecast.model.hourly.HourlyForecastResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApi {

    @GET("/locations/v1/cities/autocomplete")
    suspend fun getCitiesByText(
        @Query("q") search: String,
    ): List<CityResponse>

    @GET("/forecasts/v1/hourly/12hour/{idLocation}")
    suspend fun get12HoursForecast(
        @Path("idLocation") location: Int,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true,
    ): List<HourlyForecastResponse>

    @GET("/forecasts/v1/daily/5day/{idLocation}")
    suspend fun get5DaysForecast(
        @Path("idLocation") location: Int,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true,
    ): ForecastResponse
}