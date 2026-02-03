package com.spoonofcode.core.recaptcha

class IosRecaptchaPlatform(
    private val siteKey: String,
) : RecaptchaPlatform {
    override suspend fun execute(action: String): String {
        return RecaptchaWebViewRunner.run(siteKey = siteKey, action = action)
    }
}
