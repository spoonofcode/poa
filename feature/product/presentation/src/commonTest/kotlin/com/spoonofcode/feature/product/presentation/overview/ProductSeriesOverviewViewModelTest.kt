package com.spoonofcode.feature.product.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductMockData.PRODUCTS
import com.spoonofcode.feature.product.presentation.ProductMockData.PRODUCT_1
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverViewViewAction
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewViewModel
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewViewState
import dev.mokkery.matcher.any
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
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = ProductSeriesOverviewViewState(),
                ),
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
                expected = ViewState(
                    data = ProductSeriesOverviewViewState(
                        productSeries = PRODUCTS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

//        verifySuspend {
//            productRepository.readAll()
//        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `checked product series`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                ProductSeriesOverViewViewAction.InitView
            )

            assertEquals(
                expected = ViewState(
                    data = ProductSeriesOverviewViewState(
                        productSeries = PRODUCTS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(
                ProductSeriesOverViewViewAction.CheckedProductSeries(
                    productSeriesId = PRODUCT_1.seriesId,
                )
            )

            assertEquals(
                expected = ViewState(
                    data = ProductSeriesOverviewViewState(
                        productSeries = PRODUCTS,
                        checkedProductSeriesIds = setOf(PRODUCT_1.seriesId),
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(ProductSeriesOverViewViewAction.NavigateToNotification)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModule.NotificationEditScreen()) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}