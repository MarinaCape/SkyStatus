package com.skystatus.domain.repository

import com.skystatus.domain.model.Sunset

interface SunsetRepository {
    suspend fun getQualitySun(param: String): Sunset
}