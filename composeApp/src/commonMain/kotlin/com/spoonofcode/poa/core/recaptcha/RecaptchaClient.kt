package com.spoonofcode.poa.core.recaptcha

class RecaptchaClient(
    private val platform: RecaptchaPlatform
) {
    suspend fun execute(action: String): String = platform.execute(action)
}
