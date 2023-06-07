package com.skystatus.domain.repository

import com.skystatus.domain.entity.City

interface ForecastRepository {
    suspend fun getSearchCities(param: String): List<City>
}