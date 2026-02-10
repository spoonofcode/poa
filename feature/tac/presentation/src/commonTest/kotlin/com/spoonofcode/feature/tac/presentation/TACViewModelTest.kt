package com.spoonofcode.feature.tac.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.tac.presentation.di.tacPresentationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TACViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: TACViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(tacPresentationTestModule)
        super.setup()
        viewModel = getSut()
    }

    @AfterTest
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = TACViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}