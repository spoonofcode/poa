package com.spoonofcode.poa.feature.product.series.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.product.di.productTestModule
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductSeriesOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProductSeriesOverviewViewModel
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
                expected = ProductSeriesOverviewViewState(),
                actual = awaitItem()
            )
        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProductSeriesOverViewViewAction.InitView)

            assertEquals(
                expected = ProductSeriesOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    productSeries = PRODUCTS,
                ),
                actual = awaitItem()
            )
        }

//        verifySuspend {
//            productRepository.readAll()
//        }
    }

    @Test
    fun `checked product series`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                ProductSeriesOverViewViewAction.InitView
            )

            assertEquals(
                expected = ProductSeriesOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    productSeries = PRODUCTS,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(
                ProductSeriesOverViewViewAction.CheckedProductSeries(
                    productSeriesId = PRODUCT_1.seriesId,
                )
            )

            assertEquals(
                expected = ProductSeriesOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    productSeries = PRODUCTS,
                    checkedProductSeriesIds = setOf(PRODUCT_1.seriesId),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(ProductSeriesOverViewViewAction.NavigateToNotification)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<NotificationEditScreen>()) }
    }
}