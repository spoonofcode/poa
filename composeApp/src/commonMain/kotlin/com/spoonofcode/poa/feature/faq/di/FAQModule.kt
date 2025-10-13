package com.spoonofcode.poa.feature.faq.di

import com.spoonofcode.poa.feature.faq.FAQViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val faqModule = module {
    viewModelOf(::FAQViewModel)
}