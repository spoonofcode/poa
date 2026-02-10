package com.spoonofcode.feature.partner.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.partner.data.test.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNERS
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerOverviewViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
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
        val categoryName = "Category name"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(PartnerOverviewViewAction.ClickCategory(categoryName = categoryName))

            assertEquals(
                expected = ViewState(
                    data = PartnerOverviewViewState(
                        selectedPartnerCategories = setOf(categoryName),
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

        verifySuspend { routeResolver.resolve(PartnerModule.PartnerDetailsScreen(partnerId = PARTNER_1.id)) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}