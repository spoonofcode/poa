package com.spoonofcode.feature.notification.presentation.type

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
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
class NotificationTypeViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationTypeViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationPresentationTestModule)
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
                    data = NotificationTypeViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notification screen for all customers`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.SendNotificationToAllCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationEdit()) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner customers overview screen`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.SendNotificationToSelectedPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(PartnerModuleRoute.CustomerOverview) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner product series overview screen`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.SendNotificationToSelectedPartnerProductSeries)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProductModuleRoute.ProductSeriesOverview) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to notification screen for all partner customers`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.SendNotificationToAllPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationEdit()) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}