package com.spoonofcode.poa.feature.scan

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.scan.di.scanModule
import com.spoonofcode.poa.feature.scan.di.scanTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ScanViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ScanViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(scanTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ScanViewState(),
                actual = awaitItem()
            )
        }
    }
}