package com.spoonofcode.poa.feature.product.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsScreen
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsScreen
import com.spoonofcode.poa.feature.product.di.productTestModule
import dev.mokkery.matcher.any
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewModel
    private lateinit var userRepository: UserRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(productTestModule)
        super.setup()
        userRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.InitView)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    initProducts = PRODUCTS,
                    filteredProducts = PRODUCTS,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            userRepository.readUserProducts(any())
        }
    }

    @Test
    fun `change search text`() = runTest {
        val searchText = "New search text"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.ChangeSearchText(searchText = searchText))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState(
                    searchText = searchText,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `select product`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.SelectProduct(productId = PRODUCT_1.id))
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsScreen>()) }
    }
}