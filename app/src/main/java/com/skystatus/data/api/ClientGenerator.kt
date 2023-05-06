package com.skystatus.data.api

import kotlin.reflect.KClass

interface ClientGenerator {
    fun <T : Any> generate(dataSource: KClass<T>): T
}