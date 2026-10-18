package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.home.data.test.TOTAL_POINTS
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

class GetUserRewardsPointsUseCaseTest : BaseTest() {

    private lateinit var getUserRewardsPointsUseCase: GetUserRewardsPointsUseCase
    private lateinit var rewardRepository: RewardRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(homeDomainTestModule)
        super.beforeTest()
        rewardRepository = getKoin().get()
        getUserRewardsPointsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with reward points`() = runTest {
        val result = getUserRewardsPointsUseCase()

        assertTrue(result.isSuccess)
        assertEquals(TOTAL_POINTS, result.getOrNull())
        verifySuspend { rewardRepository.getUserRewardsPoints() }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val exception = Exception("Error getting reward points")
        everySuspend { rewardRepository.getUserRewardsPoints() } returns Result.failure(exception)

        val result = getUserRewardsPointsUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { rewardRepository.getUserRewardsPoints() }
    }
}
