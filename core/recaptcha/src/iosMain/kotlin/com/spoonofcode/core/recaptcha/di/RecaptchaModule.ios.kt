package com.spoonofcode.core.recaptcha.di

import com.spoonofcode.core.recaptcha.BuildKonfig
import com.spoonofcode.core.recaptcha.IosRecaptchaPlatform
import com.spoonofcode.core.recaptcha.RecaptchaPlatform
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformRecaptchaModule = module {
    single {
        IosRecaptchaPlatform(
            siteKey = BuildKonfig.RECAPTCHA_SITE_KEY_IOS,
        )
    } bind RecaptchaPlatform::class
}
