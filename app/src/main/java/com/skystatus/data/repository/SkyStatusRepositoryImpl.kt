package com.skystatus.data.repository

import com.skystatus.data.api.ClientGenerator
import com.skystatus.data.api.SkyStatusApi
import com.skystatus.domain.repository.SkyStatusRepository
import javax.inject.Inject

class SkyStatusRepositoryImpl @Inject constructor(
    private val clientGenerator: ClientGenerator
): SkyStatusRepository {
    override suspend fun getSkyStatus(): Any {
        val api = clientGenerator.generate(SkyStatusApi::class)
        val response = api.getSkyStatus()
        return ""//response.toDomain()
    }
}