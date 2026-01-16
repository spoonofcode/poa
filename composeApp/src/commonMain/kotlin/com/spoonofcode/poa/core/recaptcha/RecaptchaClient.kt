package com.spoonofcode.poa.core.recaptcha

interface RecaptchaClient {
    suspend fun execute(action: String): String
}