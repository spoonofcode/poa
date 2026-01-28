package com.spoonofcode.poa.feature.tac

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.poa.feature.tac.di.tacTestModule
import com.spoonofcode.poa.feature.tac.presentation.TACViewModel
import com.spoonofcode.poa.feature.tac.presentation.TACViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TACViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: TACViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(tacTestModule)
        super.setup()
        viewModel = getSut()
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