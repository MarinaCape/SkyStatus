package com.skystatus.domain.interactor.sunset

import com.skystatus.data.api.sunset.model.SunsetResponse
import com.skystatus.domain.core.UseCase
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.repository.ForecastRepository
import com.skystatus.domain.repository.SunsetRepository
import javax.inject.Inject

class GetSunsetQualityUseCase @Inject constructor(
    private val sunsetRepository: SunsetRepository
) : UseCase<SunsetResponse, String>() {

    override suspend fun run(params: String): SunsetResponse {
        return sunsetRepository.getQualitySun(params)
    }
}