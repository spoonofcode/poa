package com.spoonofcode.feature.home.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.home.presentation.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.feature.home.presentation.di.homePresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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

//            assertEquals(
//                expected = HomeViewState(
//                    screenState = ScreenState.LOADING,
//                ),
//                actual = awaitItem()
//            )

            assertEquals(
                expected = ViewState(
                    data = HomeViewState(
                        dailyCheckInStatus = DAILY_CHECK_IN_STATUS_1,
                        totalPoints = TOTAL_POINTS
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
                        totalPoints = TOTAL_POINTS
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

        verifySuspend { routeResolver.resolve(NotificationModule.NotificationOverviewScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}