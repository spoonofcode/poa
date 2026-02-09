package com.spoonofcode.feature.product.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.product.di.productTestModule
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.details.ProductDetailsScreen
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewViewAction
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewViewModel
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewViewState
import dev.mokkery.matcher.ofType
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
    override fun setup() {
        modules = arrayOf(productTestModule)
        super.setup()
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

        verifySuspend { viewModelNavigator.push(ofType<ProductDetailsScreen>()) }
    }
}