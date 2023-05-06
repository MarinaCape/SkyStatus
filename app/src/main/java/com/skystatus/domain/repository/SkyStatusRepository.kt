package com.skystatus.domain.repository

interface SkyStatusRepository {
    suspend fun getSkyStatus(): Any
}