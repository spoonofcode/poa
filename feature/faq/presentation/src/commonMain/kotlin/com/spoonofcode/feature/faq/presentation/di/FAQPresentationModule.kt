package com.spoonofcode.feature.faq.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.FAQModule
import com.spoonofcode.feature.faq.presentation.FAQModuleRouteResolver
import com.spoonofcode.feature.faq.presentation.FAQViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val faqPresentationModule = module {
    factoryOf(::FAQModuleRouteResolver) { bind<ModuleRouteResolver<FAQModule>>() }

    viewModelOf(::FAQViewModel)
}