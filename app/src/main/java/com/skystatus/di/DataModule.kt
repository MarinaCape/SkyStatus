package com.skystatus.di

import com.skystatus.data.api.ApiClientGenerator
import com.skystatus.data.repository.ForecastRepositoryImpl
import com.skystatus.domain.repository.ForecastRepository
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
    fun provideSkyStatusRepository(clientGenerator: ApiClientGenerator): ForecastRepository =
        ForecastRepositoryImpl(clientGenerator)
}