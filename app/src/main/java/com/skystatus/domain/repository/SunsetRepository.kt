package com.skystatus.domain.repository

import com.skystatus.domain.entity.Sunset

interface SunsetRepository {
    suspend fun getQualitySun(param: String): Sunset
}