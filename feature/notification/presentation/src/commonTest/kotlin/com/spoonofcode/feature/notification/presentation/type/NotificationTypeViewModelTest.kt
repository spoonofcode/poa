package com.spoonofcode.feature.notification.presentation.type

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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
        viewModel.onAction(NotificationTypeViewAction.NavigateToAllCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModule.NotificationEditScreen()) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner customers overview screen`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.NavigateToPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(PartnerModule.CustomerOverviewScreen(partnerId = any())) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner product series overview screen`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.NavigateToPartnerProductSeries)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProductModule.ProductSeriesOverviewScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to notification screen for all partner customers`() = runTest {
        viewModel.onAction(NotificationTypeViewAction.NavigateToAllPartnerCustomers)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModule.NotificationEditScreen()) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}