package com.skystatus.data.repository

import com.skystatus.data.api.ClientGenerator
import com.skystatus.data.api.ForecastApi
import com.skystatus.data.repository.mapper.toDomain
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val clientGenerator: ClientGenerator
): ForecastRepository {
    override suspend fun getSearchCities(param: String): List<City> {
        val api = clientGenerator.generate(ForecastApi::class)
        return api.getCitiesByText(param).toDomain()
    }
    override suspend fun get12HoursForecast(param: Int): List<HourlyForecast> {
        val api = clientGenerator.generate(ForecastApi::class)
        return api.get12HoursForecast(param).toDomain()
    }

    override suspend fun get5DaysForecast(param: Int): List<DailyForecast> {
        val api = clientGenerator.generate(ForecastApi::class)
        return api.get5DaysForecast(param).dailyForecasts.toDomain()
    }
}