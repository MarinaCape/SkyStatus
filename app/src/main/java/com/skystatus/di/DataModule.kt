package com.skystatus.di

import com.skystatus.data.api.ApiClientGenerator
import com.skystatus.data.repository.SkyStatusRepositoryImpl
import com.skystatus.domain.repository.SkyStatusRepository
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
    fun provideSkyStatusRepository(clientGenerator: ApiClientGenerator): SkyStatusRepository =
        SkyStatusRepositoryImpl(clientGenerator)
}