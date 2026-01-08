package com.spoonofcode.poa.core.recaptcha

import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class RecaptchaClient(
    private val platform: RecaptchaPlatform
) {
    suspend fun execute(action: String): String = platform.execute(action)
}
