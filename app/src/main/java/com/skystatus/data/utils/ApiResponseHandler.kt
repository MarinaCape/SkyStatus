package com.insurama.core.data.datasource.api.utils


import com.skystatus.domain.error.HttpException
import com.skystatus.domain.error.NetworkError
import com.skystatus.domain.error.RepositoryException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException

inline fun <reified T, reified R : ErrorMessageResponse> handleResponse(apiCall: () -> T): T {
    try {
        return apiCall()
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> throw NetworkError()
            is retrofit2.HttpException -> {
                val error: ErrorResponse<R>? = getError(throwable)
                throw HttpException(throwable.code(), error?.getErrorMessage())
            }
            else -> throw RepositoryException(throwable.message.toString())
        }
    }
}

inline fun <reified R: ErrorMessageResponse>getError(error: retrofit2.HttpException): ErrorResponse<R>? {
    return try {
        error.response()?.errorBody()?.string()?.let {
            val type = Types.newParameterizedType(
                ErrorResponse::class.java,
                R::class.java
            )
            val adapter = Moshi.Builder()
                .build()
                .adapter<ErrorResponse<R>>(type)
                .lenient()
            adapter.fromJson(it)
        }
    } catch (e: Exception) {
        null
    }
}
