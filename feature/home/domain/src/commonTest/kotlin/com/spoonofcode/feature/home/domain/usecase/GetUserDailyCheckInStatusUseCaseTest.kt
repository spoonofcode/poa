package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.home.data.test.DailyCheckInStatusMockData
import com.spoonofcode.feature.home.domain.di.homeDomainTestModule
import com.spoonofcode.feature.home.domain.repository.RewardRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUserDailyCheckInStatusUseCaseTest : BaseTest() {

    private lateinit var getUserDailyCheckInStatusUseCase: GetUserDailyCheckInStatusUseCase
    private lateinit var rewardRepository: RewardRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(homeDomainTestModule)
        super.beforeTest()
        rewardRepository = getKoin().get()
        getUserDailyCheckInStatusUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with daily check-in status`() = runTest {
        val result = getUserDailyCheckInStatusUseCase()

        assertTrue(result.isSuccess)
        assertEquals(DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1, result.getOrNull())
        verifySuspend { rewardRepository.getUserDailyCheckInStatus() }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val exception = Exception("Error getting check-in status")
        everySuspend { rewardRepository.getUserDailyCheckInStatus() } returns Result.failure(exception)

        val result = getUserDailyCheckInStatusUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { rewardRepository.getUserDailyCheckInStatus() }
    }
}
