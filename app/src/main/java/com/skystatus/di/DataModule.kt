package com.skystatus.di

import com.skystatus.data.api.ApiClientGenerator
import com.skystatus.data.api.ApiClientSunsetGenerator
import com.skystatus.data.repository.forecast.ForecastRepositoryImpl
import com.skystatus.data.repository.sunset.SunsetRepositoryImpl
import com.skystatus.domain.repository.ForecastRepository
import com.skystatus.domain.repository.SunsetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideForecastRepository(clientGenerator: ApiClientGenerator): ForecastRepository =
        ForecastRepositoryImpl(clientGenerator)

    @Provides
    @Singleton
    fun provideSunsetRepository(clientGenerator: ApiClientSunsetGenerator): SunsetRepository =
        SunsetRepositoryImpl(clientGenerator)
}