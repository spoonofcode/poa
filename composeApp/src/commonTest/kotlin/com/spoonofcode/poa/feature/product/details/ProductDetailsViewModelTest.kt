package com.spoonofcode.poa.feature.product.details

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.poa.core.data.repository.ProductDataSource
import com.spoonofcode.poa.feature.profile.domain.UserDataSource
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.di.productTestModule
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewModel
    private lateinit var productDataSource: ProductDataSource
    private lateinit var userDataSource: UserDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(productTestModule)
        super.setup()
        productDataSource = getKoin().get()
        userDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productDataSource.read(PRODUCT_1.id)
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { productDataSource.read(any()) } throws Exception("not found")

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewState(
                    screenState = ScreenState.ERROR
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productDataSource.read(PRODUCT_1.id)
        }
    }

    @Test
    fun `add product to user success`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.AddProductToUser)
        advanceUntilIdle()

        verifySuspend {
            userDataSource.addProductToUser(PRODUCT_1.id, USER_1.id)
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }

    @Test
    fun `add product to user error`() = runTest {
        everySuspend {
            userDataSource.addProductToUser(
                any(),
                any()
            )
        } throws Exception("add product to user error")

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.AddProductToUser)
        advanceUntilIdle()

        verifySuspend {
            userDataSource.addProductToUser(PRODUCT_1.id, USER_1.id)
        }

        verifySuspend(mode = exactly(0)) {
            viewModelNavigator.pop()
        }
    }

    @Test
    fun `edit product success`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.EditProduct)
        advanceUntilIdle()

        verifySuspend {
            viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.product.presentation.edit.ProductEditScreen>())
        }
    }

    @Test
    fun `delete product from user success`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.DeleteProductFromUser)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewState(
                    screenState = ScreenState.LOADING,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )
        }


        verifySuspend {
            userDataSource.deleteProductFromUser(any(), any())
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

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.InitView(PRODUCT_1.id))
        advanceUntilIdle()

        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction.DeleteProductFromUser)
        advanceUntilIdle()

        verifySuspend {
            userDataSource.deleteProductFromUser(any(), any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}