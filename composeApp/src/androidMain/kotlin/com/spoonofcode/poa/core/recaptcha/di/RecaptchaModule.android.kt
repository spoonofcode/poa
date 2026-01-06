package com.spoonofcode.poa.core.recaptcha.di

import com.spoonofcode.poa.core.recaptcha.AndroidRecaptchaPlatform
import com.spoonofcode.poa.core.recaptcha.RecaptchaPlatform
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

const val SITE_KEY = "6LejYTUsAAAAAA-suNhCr1DkfzbpcHmZpXWIG-E3 "

actual val platformRecaptchaModule = module {
    single {
        AndroidRecaptchaPlatform(
            application = androidApplication(),
            siteKey = SITE_KEY
        )
    } bind RecaptchaPlatform::class
}