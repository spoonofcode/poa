package com.spoonofcode.feature.partner.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.PartnerUserMockData.PARTNER_USERS
import com.spoonofcode.poa.core.data.mockdata.PartnerUserMockData.PARTNER_USER_1
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.di.partnerTestModule
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewAction
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewState
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerCustomerOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerCustomerOverviewViewModel
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
        partnerRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = PartnerCustomerOverviewViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                PartnerCustomerOverviewViewAction.InitView(
                    partnerId = PARTNER_1.id,
                )
            )

            assertEquals(
                expected = ViewState(
                    data = PartnerCustomerOverviewViewState(
                        partnerUsers = PARTNER_USERS,
                        checkedUserIds = emptySet(),
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            partnerRepository.getPartnerUsers(PARTNER_1.id)
        }
    }

    @Test
    fun `checked customer`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                PartnerCustomerOverviewViewAction.InitView(
                    partnerId = PARTNER_1.id,
                )
            )

            assertEquals(
                expected = ViewState(
                    data = PartnerCustomerOverviewViewState(
                        partnerUsers = PARTNER_USERS,
                        checkedUserIds = emptySet(),
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(
                PartnerCustomerOverviewViewAction.CheckedCustomer(
                    userId = PARTNER_USER_1.id,
                )
            )

            assertEquals(
                expected = ViewState(
                    data = PartnerCustomerOverviewViewState(
                        partnerUsers = PARTNER_USERS,
                        checkedUserIds = setOf(PARTNER_USER_1.id),
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(PartnerCustomerOverviewViewAction.NavigateToNotification)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen>()) }
    }
}