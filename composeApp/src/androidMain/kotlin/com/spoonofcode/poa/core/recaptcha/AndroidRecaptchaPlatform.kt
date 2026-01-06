package com.spoonofcode.poa.core.recaptcha

import android.app.Application
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction

class AndroidRecaptchaPlatform(
    private val application: Application,
    private val siteKey: String,
) : RecaptchaPlatform {

    override suspend fun execute(action: String): String {
        val client = Recaptcha.getClient(application, siteKey).getOrThrow()
        val result = client.execute(RecaptchaAction.custom(action)).getOrThrow()
        return result
    }
}
