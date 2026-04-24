package com.spoonofcode.feature.scan.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.scan.presentation.di.scanPresentationTestModule
import dev.mokkery.matcher.any
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
    override fun beforeTest() {
        modules = arrayOf(scanPresentationTestModule)
        super.beforeTest()
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

        verifySuspend { routeResolver.resolve(ProductModuleRoute.ProductDetails(productId = PRODUCT_1.id)) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}