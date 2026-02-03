package com.spoonofcode.core.recaptcha.di

import com.spoonofcode.core.data.recaptcha.AndroidRecaptchaPlatform
import com.spoonofcode.core.data.recaptcha.RecaptchaPlatform
import com.spoonofcode.poa.BuildKonfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformRecaptchaModule = module {
    single {
        AndroidRecaptchaPlatform(
            application = androidApplication(),
            siteKey = BuildKonfig.RECAPTCHA_SITE_KEY_ANDROID
        )
    } bind RecaptchaPlatform::class
}