package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.feature.faq.presentation.FAQViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val faqModule = module {
    viewModelOf(::FAQViewModel)
}