package com.skystatus.domain.use_case.sunset

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.model.Sunset
import com.skystatus.domain.repository.SunsetRepository
import javax.inject.Inject

class GetSunsetQualityUseCase @Inject constructor(
    private val sunsetRepository: SunsetRepository
) : UseCase<Sunset, String>() {

    override suspend fun run(params: String): Sunset {
        return sunsetRepository.getQualitySun(params)
    }
}