package com.spoonofcode.core.data.recaptcha.di

import com.spoonofcode.poa.BuildKonfig
import com.spoonofcode.core.data.recaptcha.IosRecaptchaPlatform
import com.spoonofcode.core.data.recaptcha.RecaptchaPlatform
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformRecaptchaModule = module {
    single {
        IosRecaptchaPlatform(
            siteKey = BuildKonfig.RECAPTCHA_SITE_KEY_IOS,
        )
    } bind RecaptchaPlatform::class
}
