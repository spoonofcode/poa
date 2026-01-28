package com.spoonofcode.core.data.recaptcha

interface RecaptchaPlatform {
    suspend fun execute(action: String): String // zwraca token
}
