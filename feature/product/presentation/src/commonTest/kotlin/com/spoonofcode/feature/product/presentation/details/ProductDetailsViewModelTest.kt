package com.spoonofcode.feature.product.presentation.details

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.product.data.test.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.domain.model.ProductStatus
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import kotlinx.coroutines.test.runTest
import org.koin.core.parameter.parametersOf
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductDetailsViewModelTest : BaseViewModelTest() {

    private val navigationParamProductId = "1"

    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        viewModel = getKoin().get(parameters = { parametersOf(navigationParamProductId) })
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            assertEquals(
                expected = ProductDetailsViewState(
                    product = PRODUCT_1,
                    productStatus = ProductStatus.UNASSIGNED,
                ),
                actual = viewState.awaitContentState()
            )
        }
    }

}