package com.spoonofcode.feature.auth.domain.di

import com.spoonofcode.core.data.getPlatform
import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.feature.auth.data.test.di.authDataTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import org.koin.dsl.module

val authDomainTestModule =
    module {
        includes(
            authDataTestModule,
            authDomainModule,
        )
        single { getPlatform() }
        single<RecaptchaClient> {
            mock {
                everySuspend { execute(any()) } returns "test recaptcha token"
            }
        }
    }
