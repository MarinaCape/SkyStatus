package com.insurama.core.data.datasource.api.utils

import com.squareup.moshi.Json

class ErrorResponse<T: ErrorMessageResponse>(
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "errors") val errors: T
) {
    fun getErrorMessage() = message ?: errors.getMessage()
}