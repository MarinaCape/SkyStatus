package com.skystatus.data.repository.sunset

import com.skystatus.data.api.ClientGenerator
import com.skystatus.data.api.sunset.SunsetApi
import com.skystatus.data.api.sunset.model.SunsetResponse
import com.skystatus.domain.repository.SunsetRepository
import javax.inject.Inject

class SunsetRepositoryImpl @Inject constructor(
    private val clientGenerator: ClientGenerator
): SunsetRepository {

    override suspend fun getQualitySun(param: String): SunsetResponse {
        val api = clientGenerator.generate(SunsetApi::class)
        return api.getQualitySunset(param)
    }
}