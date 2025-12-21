package com.spoonofcode.poa.feature.partner.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNERS
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
import com.spoonofcode.poa.feature.partner.presentation.overview.PartnerOverviewViewAction
import com.spoonofcode.poa.feature.partner.presentation.overview.PartnerOverviewViewModel
import com.spoonofcode.poa.feature.partner.presentation.overview.PartnerOverviewViewState
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
                expected = PartnerOverviewViewState(),
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
                expected = PartnerOverviewViewState(
                    searchText = searchText,
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
                expected = PartnerOverviewViewState(
                    selectedPartnerCategories = setOf(categoryId),
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
                expected = PartnerOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    initPartners = PARTNERS,
                    filteredPartners = PARTNERS,
                    partnerCategories = PARTNER_CATEGORIES,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `select partner`() = runTest {
        viewModel.onAction(PartnerOverviewViewAction.SelectPartner(partnerId = PARTNER_1.id))
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsScreen>()) }
    }
}