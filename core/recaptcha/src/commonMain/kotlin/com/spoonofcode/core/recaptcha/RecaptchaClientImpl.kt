package com.spoonofcode.core.recaptcha

class RecaptchaClientImpl(
    private val platform: RecaptchaPlatform
) :RecaptchaClient {
    override suspend fun execute(action: String): String = platform.execute(action)
}
