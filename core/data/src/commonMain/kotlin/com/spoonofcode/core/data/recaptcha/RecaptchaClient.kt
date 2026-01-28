package com.spoonofcode.core.data.recaptcha

interface RecaptchaClient {
    suspend fun execute(action: String): String
}