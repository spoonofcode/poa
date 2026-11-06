package com.spoonofcode.feature.product.presentation.details

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.awaitLoadingState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.model.ProductStatus
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductEvent
import com.spoonofcode.feature.product.presentation.ProductEventBus
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import com.spoonofcode.feature.product.presentation.product_updated
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
import com.spoonofcode.feature.product.presentation.Res as ProductRes

class ProductDetailsViewModelTest : BaseViewModelTest() {
    private val navigationParamProductId = "1"

    private lateinit var viewModel: ProductDetailsViewModel
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
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(PRODUCT_1, product)
                    assertEquals(ProductStatus.OWNED_BY_OTHER, productStatus)
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { productRepository.getProduct(navigationParamProductId) } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `add product to profile success`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.AddProductToProfile)

                with(viewState.awaitContentState()) {
                    assertTrue(isAddProductToProfileButtonLoading)
                }

                verifySuspend { productRepository.addProductToUser(PRODUCT_1.id) }
                verifySuspend {
                    viewModelNavigator.replaceAll(
                        listOf(ProductModuleRoute.ProductOverview),
                    )
                }
            }
        }

    @Test
    fun `add product to profile error`() =
        runTest {
            everySuspend { productRepository.addProductToUser(any()) } returns
                Result.failure(
                    Exception("Error adding product"),
                )

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.AddProductToProfile)

                with(viewState.awaitContentState()) {
                    assertTrue(isAddProductToProfileButtonLoading)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isAddProductToProfileButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )

                verifySuspend { productRepository.addProductToUser(PRODUCT_1.id) }
            }
        }

    @Test
    fun `edit product`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.EditProduct)

                verifySuspend {
                    viewModelNavigator.push(ProductModuleRoute.ProductEdit(productId = PRODUCT_1.id))
                }
            }
        }

    @Test
    fun `delete product from profile`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.DeleteProductFromProfile)

                with(viewState.awaitContentState()) {
                    assertTrue(isDeleteProductDialogVisible)
                }
            }
        }

    @Test
    fun `cancel delete product from profile`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.DeleteProductFromProfile)
                with(viewState.awaitContentState()) {
                    assertTrue(isDeleteProductDialogVisible)
                }

                onAction(ProductDetailsViewAction.CancelDeleteProductFromProfile)
                with(viewState.awaitContentState()) {
                    assertFalse(isDeleteProductDialogVisible)
                }
            }
        }

    @Test
    fun `confirm delete product from profile success`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.ConfirmDeleteProductFromProfile)

                with(viewState.awaitContentState()) {
                    assertTrue(isDeleteProductFromProfileButtonLoading)
                    assertFalse(isDeleteProductDialogVisible)
                }

                verifySuspend { productRepository.deleteProductFromUser(PRODUCT_1.id) }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `confirm delete product from profile error`() =
        runTest {
            everySuspend { productRepository.deleteProductFromUser(any()) } returns
                Result.failure(
                    Exception("Error deleting product"),
                )

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

            runVmTest(viewModel) {
                onAction(ProductDetailsViewAction.ConfirmDeleteProductFromProfile)

                with(viewState.awaitContentState()) {
                    assertTrue(isDeleteProductFromProfileButtonLoading)
                    assertFalse(isDeleteProductDialogVisible)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isDeleteProductFromProfileButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )

                verifySuspend { productRepository.deleteProductFromUser(PRODUCT_1.id) }
            }
        }

    @Test
    fun `observe product updated event`() =
        runTest {
            val productEventBus: ProductEventBus = getKoin().get()

            runVmTest(viewModel) {
                productEventBus.emitDetails(ProductEvent.ProductUpdated)

                viewState.awaitLoadingState()
                with(viewState.awaitContentState()) {
                    assertEquals(PRODUCT_1, product)
                    assertEquals(ProductStatus.OWNED_BY_OTHER, productStatus)
                }
                assertEquals(
                    expected = UiText.Resource(ProductRes.string.product_updated),
                    actual = snackbarEvent.awaitItem().message,
                )
            }
        }
}
