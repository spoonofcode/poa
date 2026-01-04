package com.spoonofcode.poa.feature.notification.type

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationTypeViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(notificationTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notification screen for all customers`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllCustomers)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen>()) }
    }

    @Test
    fun `navigates to partner customers overview screen`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<CustomerOverviewScreen>()) }
    }

    @Test
    fun `navigates to partner product series overview screen`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerProductSeries)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<ProductSeriesOverviewScreen>()) }
    }

    @Test
    fun `navigates to notification screen for all partner customers`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen>()) }
    }
}