package com.spoonofcode.feature.product.presentation.overview

import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.awaitLoadingState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCTS
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductEvent
import com.spoonofcode.feature.product.presentation.ProductEventBus
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import com.spoonofcode.feature.product.presentation.product_added
import com.spoonofcode.feature.product.presentation.product_deleted
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.spoonofcode.feature.product.presentation.Res as ProductRes

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

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            with(viewState.awaitContentState()) {
                assertEquals("", searchText)
                assertEquals(PRODUCTS, initProducts)
                assertEquals(PRODUCTS, filteredProducts)
            }
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        everySuspend { productRepository.getUserProducts() } returns Result.failure(
            Exception("Test error")
        )

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitErrorState()
        }
    }

    @Test
    fun `change search text`() = runTest {
        val searchText = "Product #1"

        runVmTest(viewModel) {
            onAction(ProductOverviewViewAction.ChangeSearchText(searchText = searchText))

            with(viewState.awaitContentState()) {
                assertEquals(searchText, this.searchText)
                assertEquals(PRODUCTS, initProducts)
                assertEquals(listOf(PRODUCT_1), filteredProducts)
            }
        }
    }

    @Test
    fun `select product`() = runTest {
        runVmTest(viewModel) {
            onAction(ProductOverviewViewAction.SelectProduct(productId = PRODUCT_1.id))

            verifySuspend {
                viewModelNavigator.push(
                    ProductModuleRoute.ProductDetails(productId = PRODUCT_1.id)
                )
            }
        }
    }

    @Test
    fun `observe product added event`() = runTest {
        val productEventBus: ProductEventBus = getKoin().get()

        runVmTest(viewModel) {
            productEventBus.emitOverview(ProductEvent.ProductAdded)

            viewState.awaitLoadingState()
            with(viewState.awaitContentState()) {
                assertEquals("", searchText)
                assertEquals(PRODUCTS, initProducts)
                assertEquals(PRODUCTS, filteredProducts)
            }
            assertEquals(
                expected = UiText.Resource(ProductRes.string.product_added),
                actual = snackbarEvent.awaitItem().message
            )
        }
    }

    @Test
    fun `observe product deleted event`() = runTest {
        val productEventBus: ProductEventBus = getKoin().get()

        runVmTest(viewModel) {
            productEventBus.emitOverview(ProductEvent.ProductDeleted)

            viewState.awaitLoadingState()
            with(viewState.awaitContentState()) {
                assertEquals("", searchText)
                assertEquals(PRODUCTS, initProducts)
                assertEquals(PRODUCTS, filteredProducts)
            }
            assertEquals(
                expected = UiText.Resource(ProductRes.string.product_deleted),
                actual = snackbarEvent.awaitItem().message
            )
        }
    }
}
