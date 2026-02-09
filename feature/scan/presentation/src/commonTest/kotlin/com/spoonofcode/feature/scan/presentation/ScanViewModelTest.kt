package com.spoonofcode.feature.scan.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.scan.di.scanTestModule
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
                expected = ViewState(
                    data = ScanViewState(),
                    screenState = ScreenState.CONTENT,
                ),
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
                expected = ViewState(data = ScanViewState()),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.getProductByTagId(PRODUCT_1.tagId!!)
        }

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsScreen>()) }
    }
}