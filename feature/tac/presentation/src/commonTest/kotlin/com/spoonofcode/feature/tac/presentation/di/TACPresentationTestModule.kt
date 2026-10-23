package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.tac.data.test.di.tacDataTestModule
import com.spoonofcode.feature.tac.domain.di.tacDomainModule
import org.koin.dsl.module

val tacPresentationTestModule =
    module {
        includes(
            loggingModule,
            tacPresentationModule,
            tacDomainModule,
            tacDataTestModule,
        )
    }
