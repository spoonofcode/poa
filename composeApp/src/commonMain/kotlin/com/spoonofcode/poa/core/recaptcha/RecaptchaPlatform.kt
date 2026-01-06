package com.spoonofcode.poa.core.recaptcha

interface RecaptchaPlatform {
    suspend fun execute(action: String): String // zwraca token
}
