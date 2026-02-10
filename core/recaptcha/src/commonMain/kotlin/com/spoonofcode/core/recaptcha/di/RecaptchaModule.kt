package com.spoonofcode.core.recaptcha.di

import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.core.recaptcha.RecaptchaClientImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformRecaptchaModule: Module

val recaptchaModule: Module = module {
    includes(platformRecaptchaModule)
    singleOf(::RecaptchaClientImpl).bind(RecaptchaClient::class)
}