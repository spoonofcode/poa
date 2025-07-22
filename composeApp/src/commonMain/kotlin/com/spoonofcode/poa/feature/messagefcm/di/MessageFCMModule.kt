package com.spoonofcode.poa.feature.messagefcm.di

import com.spoonofcode.poa.feature.messagefcm.MessageFCMViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val messageFCMModule = module {
    viewModelOf(::MessageFCMViewModel)
}