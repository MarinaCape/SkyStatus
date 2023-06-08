package com.skystatus.di

import com.skystatus.BuildConfig
import com.skystatus.data.service.SettingsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(settingsService: SettingsService): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val timeOutSeconds = 30L

        return OkHttpClient
            .Builder()
            .readTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor(settingsService))
            .followRedirects(false)
            .build()
    }

    private fun tokenInterceptor(settingsService: SettingsService) = Interceptor { chain ->

        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("language", "es-ES")
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        chain.proceed(requestBuilder.build())
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_URL)
        .build()

}