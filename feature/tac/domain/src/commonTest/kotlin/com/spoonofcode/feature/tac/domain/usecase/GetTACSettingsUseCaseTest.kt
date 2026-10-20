package com.spoonofcode.feature.tac.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.tac.data.test.TACMockData
import com.spoonofcode.feature.tac.domain.di.tacDomainTestModule
import com.spoonofcode.feature.tac.domain.repository.TACRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetTACSettingsUseCaseTest : BaseTest() {

    private lateinit var getTACSettingsUseCase: GetTACSettingsUseCase
    private lateinit var tacRepository: TACRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(tacDomainTestModule)
        super.beforeTest()
        tacRepository = getKoin().get()
        getTACSettingsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with tac settings`() = runTest {
        val result = getTACSettingsUseCase()

        assertTrue(result.isSuccess)
        assertEquals(TACMockData.SETTINGS_1, result.getOrNull())
        verifySuspend { tacRepository.getTACSettings() }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val exception = Exception("Repository error")
        everySuspend { tacRepository.getTACSettings() } returns Result.failure(exception)

        val result = getTACSettingsUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { tacRepository.getTACSettings() }
    }
}
