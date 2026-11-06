package com.spoonofcode.feature.tac.data.test.di

import com.spoonofcode.feature.tac.data.test.tacRepositoryMock
import org.koin.dsl.module

val tacDataTestModule =
    module {
        includes(
            module {
                single { tacRepositoryMock() }
            },
        )
    }
