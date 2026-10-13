package com.spoonofcode.feature.auth.presentation.di

import com.spoonofcode.core.data.getPlatform
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.core.recaptcha.RecaptchaClient
import com.spoonofcode.feature.auth.data.test.di.authDataTestModule
import com.spoonofcode.feature.auth.domain.di.authDomainModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import org.koin.dsl.module

val authPresentationTestModule = module {
    includes(
        loggingModule,
        authDataTestModule,
        authDomainModule,
        authPresentationModule,
    )
    single { getPlatform() }
    single<RecaptchaClient> {
        mock {
            everySuspend { execute(any()) } returns "test recaptcha token"
        }
    }
}
