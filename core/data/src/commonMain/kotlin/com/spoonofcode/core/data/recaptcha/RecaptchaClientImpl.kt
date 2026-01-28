package com.spoonofcode.core.data.recaptcha

class RecaptchaClientImpl(
    private val platform: RecaptchaPlatform
) :RecaptchaClient {
    override suspend fun execute(action: String): String = platform.execute(action)
}
