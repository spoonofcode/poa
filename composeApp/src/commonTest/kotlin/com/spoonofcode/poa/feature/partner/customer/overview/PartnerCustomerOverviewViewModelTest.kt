package com.spoonofcode.poa.feature.partner.customer.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.poa.core.data.repository.PartnerDataSource
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewAction
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewModel
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.PartnerCustomerOverviewViewState
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
    private lateinit var partnerDataSource: PartnerDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
        partnerDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = PartnerCustomerOverviewViewState(),
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
                expected = PartnerCustomerOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    partnerUsers = USERS,
                    checkedUserIds = emptySet(),
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            partnerDataSource.readPartnerUsers(PARTNER_1.id)
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
                expected = PartnerCustomerOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    partnerUsers = USERS,
                    checkedUserIds = emptySet(),
                ),
                actual = awaitItem()
            )

            viewModel.onAction(
                PartnerCustomerOverviewViewAction.CheckedCustomer(
                    userId = USER_1.id,
                )
            )

            assertEquals(
                expected = PartnerCustomerOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    partnerUsers = USERS,
                    checkedUserIds = setOf(USER_1.id),
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