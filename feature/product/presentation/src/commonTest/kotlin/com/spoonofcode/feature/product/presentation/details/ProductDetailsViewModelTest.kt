package com.spoonofcode.feature.product.presentation.details

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(productPresentationTestModule)
        super.setup()
        productRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.getProduct(PRODUCT_1.id)
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { productRepository.getProduct(any()) } returns
                Result.failure(Exception("not found"))

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(),
                    screenState = ScreenState.ERROR,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.getProduct(PRODUCT_1.id)
        }
    }

    @Test
    fun `add product to user success`() = runTest {
        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

        viewModel.viewState.test {
            skipItems(2)

            viewModel.onAction(ProductDetailsViewAction.AddProductToUser)

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        advanceUntilIdle()

        verifySuspend {
            productRepository.addProductToUser(PRODUCT_1.id)
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }

    @Test
    fun `add product to user error`() = runTest {
        everySuspend { productRepository.addProductToUser(any()) } returns
                Result.failure(Exception("add product to user error"))

        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

        viewModel.onAction(ProductDetailsViewAction.AddProductToUser)

        viewModel.viewState.test {
            skipItems(2)

            viewModel.onAction(ProductDetailsViewAction.AddProductToUser)

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        advanceUntilIdle()

        verifySuspend {
            productRepository.addProductToUser(PRODUCT_1.id)
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.pop()
        }
    }

    @Test
    fun `edit product success`() = runTest {
        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

        advanceUntilIdle()

        viewModel.onAction(ProductDetailsViewAction.EditProduct)

        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProductModule.ProductEditScreen(productId = PRODUCT_1.id)) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `delete product from user success`() = runTest {
        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

        viewModel.viewState.test {
            skipItems(2)

            viewModel.onAction(ProductDetailsViewAction.DeleteProductFromUser)

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }
        advanceUntilIdle()

        verifySuspend {
            productRepository.deleteProductFromUser(any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }

    @Test
    fun `delete product from user error`() = runTest {
        everySuspend { productRepository.deleteProductFromUser(any()) } returns
                Result.failure(Exception("delete product error"))

        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))

        viewModel.viewState.test {
            skipItems(2)

            viewModel.onAction(ProductDetailsViewAction.DeleteProductFromUser)

            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
            assertEquals(
                expected = ViewState(
                    data = ProductDetailsViewState(
                        product = PRODUCT_1,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
        advanceUntilIdle()

        verifySuspend {
            productRepository.deleteProductFromUser(any())
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.pop()
        }
    }
}