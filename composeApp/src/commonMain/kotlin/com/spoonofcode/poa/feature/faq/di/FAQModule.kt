package com.spoonofcode.poa.feature.faq.di

import com.spoonofcode.poa.feature.faq.domain.usecase.GetFAQUseCase
import com.spoonofcode.poa.feature.faq.presentation.FAQViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val faqModule = module {
    factoryOf(::GetFAQUseCase)
    viewModelOf(::FAQViewModel)
}