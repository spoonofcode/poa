package com.spoonofcode.feature.faq.presentation.di

import com.spoonofcode.feature.faq.presentation.FAQViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val faqPresentationModule = module {
    viewModelOf(::FAQViewModel)
}