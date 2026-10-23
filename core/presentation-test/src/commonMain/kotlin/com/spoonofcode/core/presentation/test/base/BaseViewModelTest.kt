package com.spoonofcode.core.presentation.test.base

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.test.di.presentationTestModule
import com.spoonofcode.core.test.base.BaseTest
import org.koin.test.get

abstract class BaseViewModelTest : BaseTest() {
    protected lateinit var viewModelNavigator: ViewModelNavigator

    override fun beforeTest() {
        modules =
            modules.plus(
                presentationTestModule,
            )
        super.beforeTest()
        viewModelNavigator = get()
    }
}
