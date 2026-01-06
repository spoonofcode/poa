package com.spoonofcode.poa.core.recaptcha.di

import com.spoonofcode.poa.core.recaptcha.IosRecaptchaPlatform
import com.spoonofcode.poa.core.recaptcha.RecaptchaPlatform
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformRecaptchaModule = module {
    single {
        IosRecaptchaPlatform(
            siteKey = "KEY_ID"
        )
    } bind RecaptchaPlatform::class
}