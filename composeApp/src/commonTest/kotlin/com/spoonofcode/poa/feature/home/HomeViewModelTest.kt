package com.spoonofcode.poa.feature.home

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.home.di.homeTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(homeTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = HomeViewState(),
                actual = awaitItem()
            )
        }
    }
}