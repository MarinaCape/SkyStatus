package com.skystatus.domain.interactor.forecast

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.entity.City
import com.skystatus.domain.repository.ForecastRepository
import javax.inject.Inject

class GetSearchCitiesUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) : UseCase<List<City>, String>() {

    override suspend fun run(params: String): List<City> {
        return forecastRepository.getSearchCities(params)
    }
}