package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.home.domain.di.homeDomainTestModule
import com.spoonofcode.feature.home.domain.model.RewardType
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

class AddRewardToUserUseCaseTest : BaseTest() {
    private lateinit var addRewardToUserUseCase: AddRewardToUserUseCase
    private lateinit var rewardRepository: RewardRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(homeDomainTestModule)
        super.beforeTest()
        rewardRepository = getKoin().get()
        addRewardToUserUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when repository succeeds`() =
        runTest {
            val result = addRewardToUserUseCase(RewardType.DAILY_CHECK_IN)

            assertTrue(result.isSuccess)
            verifySuspend { rewardRepository.addRewardToUser(RewardType.DAILY_CHECK_IN) }
        }

    @Test
    fun `invoke returns failure when repository throws`() =
        runTest {
            val exception = Exception("Failed to add reward")
            everySuspend { rewardRepository.addRewardToUser(RewardType.DAILY_CHECK_IN) } returns Result.failure(exception)

            val result = addRewardToUserUseCase(RewardType.DAILY_CHECK_IN)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { rewardRepository.addRewardToUser(RewardType.DAILY_CHECK_IN) }
        }
}
