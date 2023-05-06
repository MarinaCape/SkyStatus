package com.skystatus.data.api

import retrofit2.http.GET

interface SkyStatusApi {

    @GET("/api/policy-claims")
    suspend fun getSkyStatus(): Any
}