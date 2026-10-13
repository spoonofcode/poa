package com.spoonofcode.feature.home.presentation

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.awaitLoadingState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.home.data.test.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.feature.home.data.test.TOTAL_POINTS
import com.spoonofcode.feature.home.domain.model.RewardType
import com.spoonofcode.feature.home.domain.repository.RewardRepository
import com.spoonofcode.feature.home.presentation.di.homePresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest : BaseViewModelTest() {

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
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitLoadingState()
            with(viewState.awaitContentState()) {
                assertEquals(DAILY_CHECK_IN_STATUS_1, dailyCheckInStatus)
                assertEquals(TOTAL_POINTS, totalPoints)
                assertEquals(2, partnerImageLinks.size)
                assertEquals(3, unreadNotificationsCount)
            }
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        val rewardRepository: RewardRepository = getKoin().get()
        everySuspend { rewardRepository.getUserDailyCheckInStatus() } returns Result.failure(
            Exception("Test error"),
        )

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitLoadingState()
            viewState.awaitErrorState()
            snackbarEvent.awaitItem()
        }
    }

    @Test
    fun `daily check in`() = runTest {
        val rewardRepository: RewardRepository = getKoin().get()

        runVmTest(viewModel) {
            onAction(HomeViewAction.DailyCheckIn)

            viewState.awaitLoadingState()
            with(viewState.awaitContentState()) {
                assertEquals(DAILY_CHECK_IN_STATUS_1, dailyCheckInStatus)
            }

            verifySuspend { rewardRepository.addRewardToUser(RewardType.DAILY_CHECK_IN) }
        }
    }

    @Test
    fun `navigate to notifications`() = runTest {
        runVmTest(viewModel) {
            onAction(HomeViewAction.NavigateToNotifications)

            verifySuspend {
                viewModelNavigator.push(NotificationModuleRoute.NotificationOverview)
            }
        }
    }
}
