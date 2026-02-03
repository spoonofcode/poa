package com.spoonofcode.core.recaptcha

interface RecaptchaPlatform {
    suspend fun execute(action: String): String
}
