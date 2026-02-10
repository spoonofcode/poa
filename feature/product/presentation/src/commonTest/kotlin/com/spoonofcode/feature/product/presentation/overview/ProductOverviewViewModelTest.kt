package com.spoonofcode.feature.product.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCTS
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductOverviewViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = ProductOverviewViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductOverviewViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProductOverviewViewState(
                        initProducts = PRODUCTS,
                        filteredProducts = PRODUCTS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.getUserProducts()
        }
    }

    @Test
    fun `change search text`() = runTest {
        val searchText = "New search text"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductOverviewViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProductOverviewViewState(
                        initProducts = PRODUCTS,
                        filteredProducts = PRODUCTS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProductOverviewViewAction.ChangeSearchText(searchText = searchText))

            assertEquals(
                expected = ViewState(
                    data = ProductOverviewViewState(
                        searchText = searchText,
                        initProducts = PRODUCTS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `select product`() = runTest {
        viewModel.onAction(ProductOverviewViewAction.SelectProduct(productId = PRODUCT_1.id))
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProductModule.ProductDetailsScreen(productId = PRODUCT_1.id)) }
        verifySuspend { viewModelNavigator.push(any()) }

    }
}