package com.spoonofcode.poa.core.recaptcha

class RecaptchaClientImpl(
    private val platform: RecaptchaPlatform
) :RecaptchaClient {
    override suspend fun execute(action: String): String = platform.execute(action)
}
