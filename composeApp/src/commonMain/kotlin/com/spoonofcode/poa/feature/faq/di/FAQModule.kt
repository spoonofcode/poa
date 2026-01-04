package com.spoonofcode.poa.feature.faq.di

import com.spoonofcode.poa.feature.faq.data.remote.RemoteFAQDataSource
import com.spoonofcode.poa.feature.faq.domain.FAQDataSource
import com.spoonofcode.poa.feature.faq.domain.usecase.GetFAQUseCase
import com.spoonofcode.poa.feature.faq.presentation.FAQViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val faqModule = module {
    singleOf(::RemoteFAQDataSource).bind<FAQDataSource>()

    factoryOf(::GetFAQUseCase)

    viewModelOf(::FAQViewModel)
}