package com.spoonofcode.poa.core.ui

import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigator
import dev.mokkery.MockMode
import dev.mokkery.mock
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.BeforeTest

abstract class BaseViewModelTest : BaseTest() {
    protected lateinit var viewModelNavigator: ViewModelNavigator

    @BeforeTest
    override fun setup() {
        modules = modules.plus(
            testModule()
        )
        super.setup()
        viewModelNavigator = get()
    }

    private fun testModule() = module {
        single { mock<ViewModelNavigator>(mode = MockMode.autofill) }
    }

}