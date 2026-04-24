package com.spoonofcode.core.network

import io.ktor.http.HttpStatusCode

object HttpStatusCodes {
    // Success
    private val HTTP_SUCCESS_MIN = HttpStatusCode.OK
    private val HTTP_SUCCESS_MAX = HttpStatusCode.MultiStatus
    val HTTP_SUCCESS_CODES = HTTP_SUCCESS_MIN..HTTP_SUCCESS_MAX

    // Client Error
    private val HTTP_CLIENT_ERROR_MIN = HttpStatusCode.BadRequest
    private val HTTP_CLIENT_ERROR_MAX = HttpStatusCode.RequestHeaderFieldTooLarge
    val HTTP_CLIENT_ERROR_CODES = HTTP_CLIENT_ERROR_MIN..HTTP_CLIENT_ERROR_MAX

    // Server errors
    private val HTTP_SERVER_ERROR_MIN = HttpStatusCode.InternalServerError
    private val HTTP_SERVER_ERROR_MAX = HttpStatusCode.InsufficientStorage
    val HTTP_SERVER_ERROR_CODES = HTTP_SERVER_ERROR_MIN..HTTP_SERVER_ERROR_MAX
}