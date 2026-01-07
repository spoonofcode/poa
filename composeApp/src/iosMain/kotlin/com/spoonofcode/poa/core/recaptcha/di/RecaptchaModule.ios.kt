package com.spoonofcode.poa.core.recaptcha.di

import com.spoonofcode.poa.core.recaptcha.IosRecaptchaPlatform
import com.spoonofcode.poa.core.recaptcha.RecaptchaPlatform
import org.koin.dsl.bind
import org.koin.dsl.module

const val SITE_KEY = "6LdzDkMsAAAAAO5nqrMIeTsEx8iSXed0J4WjCMwb"

actual val platformRecaptchaModule = module {
    single {
        IosRecaptchaPlatform(
            siteKey = SITE_KEY
        )
    } bind RecaptchaPlatform::class
}