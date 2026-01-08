package com.spoonofcode.poa.feature.product.details

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.ViewState
import com.spoonofcode.poa.feature.product.di.productTestModule
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewModel
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewState
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.ofType
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductDetailsViewModel
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
                    data = ProductDetailsViewState(),
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
        everySuspend { productRepository.getProduct(any()) } throws Exception("not found")

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
        advanceUntilIdle()

        viewModel.onAction(ProductDetailsViewAction.AddProductToUser)
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
        everySuspend {
            productRepository.addProductToUser(
                any(),
            )
        } throws Exception("add product to user error")

        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(ProductDetailsViewAction.AddProductToUser)
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

        verifySuspend {
            viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.product.presentation.edit.ProductEditScreen>())
        }
    }

    @Test
    fun `delete product from user success`() = runTest {
        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.viewState.test {
            skipItems(1)

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


        verifySuspend {
            productRepository.deleteProductFromUser(any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `delete product from user error`() = runTest {
//        everySuspend { productRepository.delete(any()) } throws Exception("delete product error")

        viewModel.onAction(ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(ProductDetailsViewAction.DeleteProductFromUser)
        advanceUntilIdle()

        verifySuspend {
            productRepository.deleteProductFromUser(any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}