package com.spoonofcode.core.presentation.test.di

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.test.network.networkManagerMock
import dev.mokkery.MockMode
import dev.mokkery.mock
import org.koin.dsl.module

internal val presentationTestModule = module {
    single { networkManagerMock() }
    single { mock<ViewModelNavigator>(mode = MockMode.autofill) }
    single { mock<VoyagerRouteResolver>(mode = MockMode.autofill) }
}