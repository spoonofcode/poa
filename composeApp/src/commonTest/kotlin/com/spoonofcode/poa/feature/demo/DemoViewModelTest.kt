package com.spoonofcode.poa.feature.demo

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.ViewState
import com.spoonofcode.poa.feature.demo.di.demoTestModule
import com.spoonofcode.poa.feature.demo.presentation.DemoViewModel
import com.spoonofcode.poa.feature.demo.presentation.DemoViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DemoViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: DemoViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(demoTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = DemoViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}