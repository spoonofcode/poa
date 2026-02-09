package com.spoonofcode.feature.partner.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNERS
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.di.partnerTestModule
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewAction
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewViewState
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerOverviewViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = PartnerOverviewViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change search text`() = runTest {
        val searchText = "New search text"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerOverviewViewAction.ChangeSearchText(searchText = searchText))

            assertEquals(
                expected = ViewState(
                    data = PartnerOverviewViewState(
                        searchText = searchText,
                    ),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `click category`() = runTest {
        val categoryId = "New category id"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerOverviewViewAction.ClickCategory(categoryId = categoryId))

            assertEquals(
                expected = ViewState(
                    data = PartnerOverviewViewState(
                        selectedPartnerCategories = setOf(categoryId),
                    ),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerOverviewViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = PartnerOverviewViewState(
                        initPartners = PARTNERS,
                        filteredPartners = PARTNERS,
                        partnerCategories = PARTNER_CATEGORIES,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `select partner`() = runTest {
        viewModel.onAction(PartnerOverviewViewAction.SelectPartner(partnerId = PARTNER_1.id))
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<PartnerDetailsScreen>()) }
    }
}