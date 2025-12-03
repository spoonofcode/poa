package com.spoonofcode.poa.feature.product.edit

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.di.productTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductEditViewModel
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
                expected = ProductEditViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductEditViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.read(PRODUCT_1.id)
        }
    }

    @Test
    fun `change custom link`() = runTest {
        val customLink = "New custom link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductEditViewAction.InitView(productId = PRODUCT_1.id))

            assertEquals(
                expected = ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProductEditViewAction.ChangeCustomLink(customLink))

            assertEquals(
                expected = ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1.copy(customLink = customLink),
                ),
                actual = awaitItem()
            )
        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `save changes`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductEditViewAction.InitView(productId = PRODUCT_1.id))

            assertEquals(
                expected = ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProductEditViewAction.SaveChanges)

            assertEquals(
                expected = ProductEditViewState(
                    screenState = ScreenState.LOADING,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.read(PRODUCT_1.id)
        }

        verifySuspend {
            productRepository.update(any(), any())
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}