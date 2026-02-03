package com.spoonofcode.core.recaptcha

import android.app.Application
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction

class AndroidRecaptchaPlatform(
    private val application: Application,
    private val siteKey: String,
) : RecaptchaPlatform {

    override suspend fun execute(action: String): String {
        val client = Recaptcha.fetchClient(application, siteKey)
        val result = client.execute(RecaptchaAction.custom(action)).getOrThrow()
        return result
    }
}
