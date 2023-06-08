package com.skystatus.domain.interactor

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.repository.ForecastRepository
import javax.inject.Inject

class Get5DaysForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) : UseCase<List<DailyForecast>, Int>() {

    override suspend fun run(params: Int): List<DailyForecast> {
        return forecastRepository.get5DaysForecast(params)
    }
}