package com.cihadseker.core.model

sealed class ServiceError {
    object NetworkError : ServiceError()
    class GenericError(val message: String?) : ServiceError()
    object TimeOutError : ServiceError()
}