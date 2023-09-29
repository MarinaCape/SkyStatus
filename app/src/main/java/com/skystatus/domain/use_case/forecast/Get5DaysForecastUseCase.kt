package com.skystatus.domain.use_case.forecast

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.model.DailyForecast
import com.skystatus.domain.repository.ForecastRepository
import javax.inject.Inject

class Get5DaysForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) : UseCase<List<DailyForecast>, Int>() {

    override suspend fun run(params: Int): List<DailyForecast> {
        return forecastRepository.get5DaysForecast(params)
    }
}