package com.skystatus.domain.repository

import com.skystatus.data.api.sunset.model.SunsetResponse
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast

interface SunsetRepository {
    suspend fun getQualitySun(param: String): SunsetResponse
}