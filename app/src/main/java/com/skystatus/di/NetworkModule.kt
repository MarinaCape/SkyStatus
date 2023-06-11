package com.skystatus.di

import android.R
import com.skystatus.BuildConfig
import com.skystatus.data.service.SettingsService
import com.skystatus.presentation.core.SkyStatusApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
            .addInterceptor(mockInterceptor())
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

    private fun mockInterceptor() = Interceptor { chain ->
        //If requested endpoint matched to targeted endpoint, we will return an mocked response.
        if (chain.request().url.toUri().toString().contains("quality")) {
            val responseString = getJsonContent()
            chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString
                        .toByteArray()
                        .toResponseBody(
                            "application/json".toMediaTypeOrNull()
                        )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //Skip the interception.
            chain.proceed(chain.request())
        }
    }

    private fun getJsonContent(): String {
        val inputStream: InputStream = SkyStatusApplication.appContext.assets.open("quality_sunset.json")
        return inputStream.bufferedReader().use { it.readText() }
    }

    @Provides
    @Singleton
    @Named("forecast")
    fun provideForecastRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_URL)
        .build()

    @Provides
    @Singleton
    @Named("sunset")
    fun provideSunsetRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_URL_SUNSET)
        .build()

}