package com.spoonofcode.poa.core.ui

import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigatorImpl
import dev.mokkery.MockMode
import dev.mokkery.mock
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.BeforeTest

abstract class BaseViewModelTest : BaseTest() {
    protected lateinit var viewModelNavigator: ViewModelNavigatorImpl

    @BeforeTest
    override fun setup() {
        modules = modules.plus(
            testModule()
        )
        super.setup()
        viewModelNavigator = get()
    }

    private fun testModule() = module {
        single { mock<ViewModelNavigatorImpl>(mode = MockMode.autofill) }
    }

}