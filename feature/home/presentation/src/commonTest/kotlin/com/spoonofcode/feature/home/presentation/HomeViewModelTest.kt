package com.spoonofcode.feature.home.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.home.data.test.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.feature.home.data.test.TOTAL_POINTS
import com.spoonofcode.feature.home.presentation.di.homePresentationTestModule
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNERS
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
class HomeViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(homePresentationTestModule)
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
                    data = HomeViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `Daily check in success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(HomeViewAction.DailyCheckIn)

            assertEquals(
                expected = ViewState(
                    data = HomeViewState(
                        dailyCheckInStatus = DAILY_CHECK_IN_STATUS_1,
                        totalPoints = TOTAL_POINTS,
                        partnerImageLinks = PARTNERS.map { partner -> partner.imageLink } as List<String>,
                        unreadNotificationsCount = 3,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `initial view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(HomeViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = HomeViewState(
                        dailyCheckInStatus = DAILY_CHECK_IN_STATUS_1,
                        totalPoints = TOTAL_POINTS,
                        partnerImageLinks = PARTNERS.map { partner -> partner.imageLink } as List<String>,
                        unreadNotificationsCount = 3,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigates to notifications screen`() = runTest {
        viewModel.onAction(HomeViewAction.NavigateToNotifications)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationOverview) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}