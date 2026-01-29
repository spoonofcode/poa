package com.spoonofcode.poa.feature.product.edit

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.product.di.productTestModule
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.edit.ProductEditViewAction
import com.spoonofcode.feature.product.presentation.edit.ProductEditViewModel
import com.spoonofcode.feature.product.presentation.edit.ProductEditViewState
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
                expected = ViewState(
                    data = ProductEditViewState(),
                ),
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
                expected = ViewState(
                    data = ProductEditViewState(product = PRODUCT_1),
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
    fun `change custom link`() = runTest {
        val customLink = "New custom link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductEditViewAction.InitView(productId = PRODUCT_1.id))

            assertEquals(
                expected = ViewState(
                    data = ProductEditViewState(product = PRODUCT_1),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProductEditViewAction.ChangeCustomLink(customLink))

            assertEquals(
                expected = ViewState(
                    data = ProductEditViewState(product = PRODUCT_1.copy(customLink = customLink)),
                    screenState = ScreenState.CONTENT,
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
                expected = ViewState(
                    data = ProductEditViewState(product = PRODUCT_1),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProductEditViewAction.SaveChanges)

            assertEquals(
                expected = ViewState(
                    data = ProductEditViewState(product = PRODUCT_1),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productRepository.getProduct(PRODUCT_1.id)
        }

        verifySuspend {
            productRepository.update(
                productId = any(),
                ownerUserId = any(),
                partnerId = any(),
                name = any(),
                description = any(),
                seriesId = any(),
                collectionName = any(),
                imageLink = any()
            )
        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}