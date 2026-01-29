package com.spoonofcode.poa.feature.home

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.poa.core.data.repository.TOTAL_POINTS
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.home.di.homeTestModule
import com.spoonofcode.feature.home.presentation.HomeViewAction
import com.spoonofcode.feature.home.presentation.HomeViewModel
import com.spoonofcode.feature.home.presentation.HomeViewState
import dev.mokkery.matcher.ofType
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
    override fun setup() {
        modules = arrayOf(homeTestModule)
        super.setup()
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

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewScreen>()) }
    }
}