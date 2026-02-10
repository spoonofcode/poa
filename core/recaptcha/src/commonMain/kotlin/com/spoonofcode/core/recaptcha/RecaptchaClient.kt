package com.spoonofcode.core.recaptcha

interface RecaptchaClient {
    suspend fun execute(action: String): String
}