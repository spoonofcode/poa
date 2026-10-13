package com.spoonofcode.feature.scan.nfc.presentation

import com.spoonofcode.core.data.PlatformType
import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.scan.nfc.presentation.di.scanPresentationTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScanViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ScanViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(scanPresentationTestModule)
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
                assertEquals(PlatformType.Android, platformType)
                assertFalse(isProductNotFoundViewVisible)
            }
        }
    }

    @Test
    fun `check product success`() = runTest {
        val tag = NfcTag(id = "1", techList = emptyList(), ndef = "https://proof-of-wear.com/nft/1")

        runVmTest(viewModel) {
            onAction(ScanViewAction.CheckProduct(tag))

            verifySuspend { productRepository.getProduct(PRODUCT_1.id) }
            verifySuspend {
                viewModelNavigator.push(
                    ProductModuleRoute.ProductDetails(productId = PRODUCT_1.id)
                )
            }
        }
    }

    @Test
    fun `check product not found invalid domain`() = runTest {
        val tag = NfcTag(id = "1", techList = emptyList(), ndef = "https://invalid-domain.com")

        runVmTest(viewModel) {
            onAction(ScanViewAction.CheckProduct(tag))

            with(viewState.awaitContentState()) {
                assertTrue(isProductNotFoundViewVisible)
            }
        }
    }

    @Test
    fun `scan again`() = runTest {
        val invalidTag = NfcTag(id = "1", techList = emptyList(), ndef = "https://invalid-domain.com")

        runVmTest(viewModel) {
            onAction(ScanViewAction.CheckProduct(invalidTag))
            with(viewState.awaitContentState()) {
                assertTrue(isProductNotFoundViewVisible)
            }

            onAction(ScanViewAction.ScanAgain)
            with(viewState.awaitContentState()) {
                assertFalse(isProductNotFoundViewVisible)
            }
        }
    }
}
