package com.spoonofcode.core.recaptcha.di

import com.spoonofcode.core.recaptcha.recaptchaMock
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val recaptchaTestModule = module {
    singleOf(::recaptchaMock)
}