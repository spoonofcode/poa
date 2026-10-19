package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import org.koin.core.parameter.parametersOf
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

class ProductEditViewModelTest : BaseViewModelTest() {

    private val navigationParamProductId = "1"

    private lateinit var viewModel: ProductEditViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        viewModel = getKoin().get { parametersOf(navigationParamProductId) }
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
                assertEquals(PRODUCT_1, product)
            }
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        everySuspend { productRepository.getProduct(navigationParamProductId) } returns Result.failure(
            Exception("Test error")
        )

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitErrorState()
        }
    }

    @Test
    fun `change custom link`() = runTest {
        val newLink = "https://newlink.com"

        runVmTest(viewModel) {
            onAction(ProductEditViewAction.ChangeCustomLink(link = newLink))

            with(viewState.awaitContentState()) {
                assertEquals(newLink, product?.customLink)
            }
        }
    }

    @Test
    fun `save changes success`() = runTest {
        runVmTest(viewModel) {
            onAction(ProductEditViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }

            verifySuspend {
                productRepository.updateProductItem(
                    productId = PRODUCT_1.id,
                    customLink = PRODUCT_1.customLink,
                )
            }
            verifySuspend { viewModelNavigator.pop() }
        }
    }

    @Test
    fun `save changes error`() = runTest {
        everySuspend {
            productRepository.updateProductItem(
                any(),
                any(),
            )
        } returns Result.failure(Exception("Error updating product"))

        val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

        runVmTest(viewModel) {
            onAction(ProductEditViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }
            with(viewState.awaitContentState()) {
                assertFalse(isSaveChangesButtonLoading)
            }
            assertEquals(
                expected = expectedErrorMessage,
                actual = snackbarEvent.awaitItem().message
            )

            verifySuspend {
                productRepository.updateProductItem(
                    productId = PRODUCT_1.id,
                    customLink = PRODUCT_1.customLink,
                )
            }
        }
    }
}
