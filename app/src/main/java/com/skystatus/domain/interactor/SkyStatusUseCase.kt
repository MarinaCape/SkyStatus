package com.skystatus.domain.interactor

import com.skystatus.domain.core.UseCase
import com.skystatus.domain.repository.SkyStatusRepository
import javax.inject.Inject

class SkyStatusUseCase @Inject constructor(
    private val claimRepository: SkyStatusRepository,
) : UseCase<Any, UseCase.None>() {

    override suspend fun run(params: None): Any {
        val claims = claimRepository.getSkyStatus()
        return claims
    }
}