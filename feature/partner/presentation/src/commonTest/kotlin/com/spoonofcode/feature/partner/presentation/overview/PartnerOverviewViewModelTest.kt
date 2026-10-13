package com.spoonofcode.feature.partner.presentation.overview

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.awaitLoadingState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.data.test.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.feature.partner.data.test.PartnerCategoriesMockData.PARTNER_CATEGORY_1
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNERS
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PartnerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerOverviewViewModel
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
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
            viewState.awaitLoadingState()
            with(viewState.awaitContentState()) {
                assertEquals("", searchText)
                assertEquals(PARTNERS, initPartners)
                assertEquals(PARTNERS, filteredPartners)
                assertEquals(PARTNER_CATEGORIES, partnerCategories)
                assertTrue(selectedPartnerCategories.isEmpty())
            }
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        everySuspend { partnerRepository.getPartners() } returns Result.failure(
            Exception("Test error"),
        )

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitLoadingState()
            viewState.awaitErrorState()
        }
    }

    @Test
    fun `change search text`() = runTest {
        val searchText = "Beauty"

        runVmTest(viewModel) {
            onAction(PartnerOverviewViewAction.ChangeSearchText(searchText = searchText))

            with(viewState.awaitContentState()) {
                assertEquals(searchText, this.searchText)
                assertEquals(listOf(PARTNER_1), filteredPartners)
            }
        }
    }

    @Test
    fun `click category`() = runTest {
        runVmTest(viewModel) {
            onAction(PartnerOverviewViewAction.ClickCategory(categoryName = PARTNER_CATEGORY_1.name))

            with(viewState.awaitContentState()) {
                assertEquals(setOf(PARTNER_CATEGORY_1.name), selectedPartnerCategories)
            }
        }
    }

    @Test
    fun `select partner`() = runTest {
        runVmTest(viewModel) {
            onAction(PartnerOverviewViewAction.SelectPartner(partnerId = PARTNER_1.id))

            verifySuspend {
                viewModelNavigator.push(
                    PartnerModuleRoute.PartnerDetails(partnerId = PARTNER_1.id),
                )
            }
        }
    }
}
