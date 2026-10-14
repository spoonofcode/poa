package com.spoonofcode.feature.tac.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.tac.domain.di.tacDomainTestModule
import com.spoonofcode.feature.tac.domain.repository.TACRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UpdateTACSettingsUseCaseTest : BaseTest() {

    private lateinit var updateTACSettingsUseCase: UpdateTACSettingsUseCase
    private lateinit var tacRepository: TACRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(tacDomainTestModule)
        super.beforeTest()
        tacRepository = getKoin().get()
        updateTACSettingsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when repository succeeds`() = runTest {
        val result = updateTACSettingsUseCase(isPrivacyPolicyAccepted = true, isTermsAndConditionsAccepted = true)

        assertTrue(result.isSuccess)
        verifySuspend { tacRepository.setTACSettings(isPrivacyPolicyAccepted = true, isTermsAndConditionsAccepted = true) }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val exception = Exception("Save error")
        everySuspend { tacRepository.setTACSettings(any(), any()) } returns Result.failure(exception)

        val result = updateTACSettingsUseCase(isPrivacyPolicyAccepted = false, isTermsAndConditionsAccepted = false)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { tacRepository.setTACSettings(isPrivacyPolicyAccepted = false, isTermsAndConditionsAccepted = false) }
    }
}
