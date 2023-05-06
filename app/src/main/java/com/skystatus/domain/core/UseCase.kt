package com.skystatus.domain.core

import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<Type, in Params> where Type : Any {

    protected abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Result<Type> {
        return withContext(io) {
            try {
                successOf(run(params))
            } catch (e: Exception) {
                Timber.e("Use case error => ${e.message} \n ${e.stackTraceToString()}")
                failureOf(RuntimeException(e.message))
            }
        }
    }

    object None
}
