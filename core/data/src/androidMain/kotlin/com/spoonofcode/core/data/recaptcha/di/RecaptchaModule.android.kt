package com.spoonofcode.core.data.recaptcha.di

//import com.spoonofcode.poa.BuildKonfig
import com.spoonofcode.core.data.recaptcha.AndroidRecaptchaPlatform
import com.spoonofcode.core.data.recaptcha.RecaptchaPlatform
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformRecaptchaModule = module {
    // TODO #109 Fix after migration to feature modules
//    single {
//        AndroidRecaptchaPlatform(
//            application = androidApplication(),
//            siteKey = BuildKonfig.RECAPTCHA_SITE_KEY_ANDROID
//        )
//    } bind RecaptchaPlatform::class
}