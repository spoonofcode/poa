package com.spoonofcode.feature.home.presentation

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.home.presentation.di.homePresentationTestModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class HomeViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(homePresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }
}