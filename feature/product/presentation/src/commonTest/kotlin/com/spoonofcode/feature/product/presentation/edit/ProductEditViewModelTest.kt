package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_2
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductEvent
import com.spoonofcode.feature.product.presentation.ProductEventBus
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
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(PRODUCT_1, product)
                    assertTrue(isSaveChangesButtonEnabled)
                    assertTrue(isViewEnable)
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
    fun `change custom link`() =
        runTest {
            val newLink = "https://newlink.com"

            runVmTest(viewModel) {
                onAction(ProductEditViewAction.ChangeCustomLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, product.customLink)
                    assertTrue(isSaveChangesButtonEnabled)
                }
            }
        }

    @Test
    fun `change custom link to empty - save changes button disabled`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductEditViewAction.ChangeCustomLink(link = ""))

                with(viewState.awaitContentState()) {
                    assertEquals("", product.customLink)
                    assertFalse(isSaveChangesButtonEnabled)
                }
            }
        }

    @Test
    fun `change redirect link`() =
        runTest {
            val newLink = "https://newredirectlink.com"

            runVmTest(viewModel) {
                onAction(ProductEditViewAction.ChangeRedirectLink(link = newLink))

                with(viewState.awaitContentState()) {
                    assertEquals(newLink, product.redirectLink)
                    assertTrue(isSaveChangesButtonEnabled)
                }
            }
        }

    @Test
    fun `save changes success - ITEM product`() =
        runTest {
            val productEventBus: ProductEventBus = getKoin().get()
            val newLink = "https://newcustomlink.com"

            runVmTest(viewModel) {
                val detailsEvents = productEventBus.detailsEvents.test()

                onAction(ProductEditViewAction.ChangeCustomLink(link = newLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newLink, product.customLink)
                }

                onAction(ProductEditViewAction.SaveChanges)

                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }

                assertEquals(ProductEvent.ProductUpdated, detailsEvents.awaitItem())

                verifySuspend {
                    productRepository.updateProductItem(
                        productId = PRODUCT_1.id,
                        customLink = newLink,
                    )
                }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `save changes success - REDIRECT product`() =
        runTest {
            everySuspend { productRepository.getProduct(navigationParamProductId) } returns
                Result.success(
                    PRODUCT_2,
                )

            val redirectViewModel: ProductEditViewModel =
                getKoin().get { parametersOf(navigationParamProductId) }
            val productEventBus: ProductEventBus = getKoin().get()
            val newLink = "https://newredirectlink.com"

            runVmTest(redirectViewModel) {
                val detailsEvents = productEventBus.detailsEvents.test()

                onAction(ProductEditViewAction.ChangeRedirectLink(link = newLink))
                with(viewState.awaitContentState()) {
                    assertEquals(newLink, product.redirectLink)
                }

                onAction(ProductEditViewAction.SaveChanges)

                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }

                assertEquals(ProductEvent.ProductUpdated, detailsEvents.awaitItem())

                verifySuspend {
                    productRepository.updateProductRedirect(
                        productId = PRODUCT_2.id,
                        redirectLink = newLink,
                    )
                }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `save changes error`() =
        runTest {
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
                    assertFalse(isViewEnable)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSaveChangesButtonLoading)
                    assertTrue(isViewEnable)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
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
