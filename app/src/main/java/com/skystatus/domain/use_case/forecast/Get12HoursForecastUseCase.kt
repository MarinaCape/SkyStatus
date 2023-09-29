package com.skystatus.domain.use_case.forecast

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.model.HourlyForecast
import com.skystatus.domain.repository.ForecastRepository
import javax.inject.Inject

class Get12HoursForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) : UseCase<List<HourlyForecast>, Int>() {

    override suspend fun run(params: Int): List<HourlyForecast> {
        return forecastRepository.get12HoursForecast(params)
    }
}