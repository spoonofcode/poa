package com.spoonofcode.feature.product.presentation.overview

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PRODUCT_SERIES_1
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PRODUCT_SERIES_LIST
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.product.presentation.di.productPresentationTestModule
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverViewViewAction
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewViewModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

class ProductSeriesOverviewViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: ProductSeriesOverviewViewModel
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productPresentationTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        viewModel = getSut()
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
                    assertEquals(PRODUCT_SERIES_LIST, productSeries)
                    assertTrue(checkedProductSeriesNames.isEmpty())
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { partnerRepository.getPartnerSeries() } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `checked product series`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductSeriesOverViewViewAction.CheckedProductSeries(name = PRODUCT_SERIES_1.name))

                with(viewState.awaitContentState()) {
                    assertEquals(setOf(PRODUCT_SERIES_1.name), checkedProductSeriesNames)
                }

                onAction(ProductSeriesOverViewViewAction.CheckedProductSeries(name = PRODUCT_SERIES_1.name))

                with(viewState.awaitContentState()) {
                    assertTrue(checkedProductSeriesNames.isEmpty())
                }
            }
        }

    @Test
    fun `checked product series max limit`() =
        runTest {
            val seriesNames = listOf("Series 1", "Series 2", "Series 3", "Series 4", "Series 5", "Series 6")
            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

            runVmTest(viewModel) {
                seriesNames.take(5).forEach { name ->
                    onAction(ProductSeriesOverViewViewAction.CheckedProductSeries(name = name))
                    with(viewState.awaitContentState()) {
                        assertTrue(checkedProductSeriesNames.contains(name))
                    }
                }

                onAction(ProductSeriesOverViewViewAction.CheckedProductSeries(name = seriesNames[5]))

                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )
            }
        }

    @Test
    fun `navigates to notification screen`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProductSeriesOverViewViewAction.CheckedProductSeries(name = PRODUCT_SERIES_1.name))
                with(viewState.awaitContentState()) {
                    assertEquals(setOf(PRODUCT_SERIES_1.name), checkedProductSeriesNames)
                }

                onAction(ProductSeriesOverViewViewAction.NavigateToNotification)

                verifySuspend {
                    viewModelNavigator.push(
                        PartnerModuleRoute.PartnerNotificationEdit(
                            selectedTopics = setOf(PartnerNotificationType.SERIE.createTopic(PRODUCT_SERIES_1.name)),
                            selectedNames = setOf(PRODUCT_SERIES_1.name),
                        ),
                    )
                }
            }
        }
}
