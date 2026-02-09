package com.spoonofcode.core.test.base

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import dev.mokkery.MockMode
import dev.mokkery.mock
import org.koin.dsl.module
import org.koin.test.get

abstract class BaseViewModelTest : BaseTest() {
    protected lateinit var viewModelNavigator: ViewModelNavigator
    protected lateinit var routeResolver: VoyagerRouteResolver

    override fun setup() {
        modules = modules.plus(
            testModule()
        )
        super.setup()
        viewModelNavigator = get()
        routeResolver = get()
    }

    private fun testModule() = module {
        single { mock<ViewModelNavigator>(mode = MockMode.autofill) }
        single { mock<VoyagerRouteResolver>(mode = MockMode.autofill) }
    }
}