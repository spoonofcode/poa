package com.spoonofcode.poa.feature.product.series.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.feature.product.di.productTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductSeriesOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductSeriesOverviewViewModel
    private lateinit var productRepository: ProductRepository
    private lateinit var userRepository: UserRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(productTestModule)
        super.setup()
        productRepository = getKoin().get()
        userRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ProductSeriesOverviewViewState(),
                actual = awaitItem()
            )
        }
    }
}