package com.skystatus.domain.repository

import com.skystatus.domain.model.City
import com.skystatus.domain.model.DailyForecast
import com.skystatus.domain.model.HourlyForecast

interface ForecastRepository {
    suspend fun getSearchCities(param: String): List<City>
    suspend fun get12HoursForecast(param: Int): List<HourlyForecast>
    suspend fun get5DaysForecast(param: Int): List<DailyForecast>
}