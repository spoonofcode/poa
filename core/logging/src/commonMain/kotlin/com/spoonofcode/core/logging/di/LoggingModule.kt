package com.spoonofcode.core.logging.di

import com.spoonofcode.core.logging.KermitLogger
import com.spoonofcode.core.logging.POALogger
import org.koin.dsl.module

val loggingModule = module {
    single<POALogger> { KermitLogger }
}
