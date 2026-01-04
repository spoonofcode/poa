package com.spoonofcode.poa.feature.product.edit

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.repository.ProductDataSource
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewModel
    private lateinit var productDataSource: ProductDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(productTestModule)
        super.setup()
        productDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction.InitView(PRODUCT_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(
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
    fun `change custom link`() = runTest {
        val customLink = "New custom link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction.InitView(productId = PRODUCT_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction.ChangeCustomLink(customLink))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction.InitView(productId = PRODUCT_1.id))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(
                    screenState = ScreenState.CONTENT,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction.SaveChanges)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewState(
                    screenState = ScreenState.LOADING,
                    product = PRODUCT_1,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            productDataSource.read(PRODUCT_1.id)
        }

        verifySuspend {
            productDataSource.update(
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