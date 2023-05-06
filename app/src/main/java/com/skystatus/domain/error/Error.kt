package com.skystatus.domain.error

class HttpException(val code: Int, override val message: String?) : RuntimeException(message)

class NetworkError : RuntimeException()

class RepositoryException(override val message: String) : RuntimeException(message)

class DomainException(override val message: String) : RuntimeException(message)