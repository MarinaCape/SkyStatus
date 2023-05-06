package com.skystatus.data.api

import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.reflect.KClass

class ApiClientGenerator @Inject constructor(private val retrofit: Retrofit) : ClientGenerator {
    override fun <T : Any> generate(dataSource: KClass<T>): T = retrofit.create(dataSource.java)
}