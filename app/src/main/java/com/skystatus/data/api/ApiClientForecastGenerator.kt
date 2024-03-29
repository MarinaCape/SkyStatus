package com.skystatus.data.api

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import kotlin.reflect.KClass

class ApiClientForecastGenerator @Inject constructor(@Named("forecast") private val retrofit: Retrofit) : ClientGenerator {
    override fun <T : Any> generate(dataSource: KClass<T>): T = retrofit.create(dataSource.java)
}