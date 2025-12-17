package com.spoonofcode.poa.feature.scan

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import com.spoonofcode.poa.feature.scan.di.scanTestModule
import com.spoonofcode.poa.feature.scan.presentation.ScanViewAction
import com.spoonofcode.poa.feature.scan.presentation.ScanViewModel
import com.spoonofcode.poa.feature.scan.presentation.ScanViewState
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ScanViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ScanViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(scanTestModule)
        super.setup()
        productRepository = getKoin().get()
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

    @Test
    fun `select product`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ScanViewAction.CheckProduct(tagId = PRODUCT_1.tagId!!))

            assertEquals(
                expected = ScanViewState(
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.readProductByTagId(PRODUCT_1.tagId!!)
        }

        verifySuspend { viewModelNavigator.push(ofType<ProductDetailsScreen>()) }
    }
}