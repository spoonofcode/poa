package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.data.test.PartnerMockData
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPartnerUseCaseTest : BaseTest() {

    private lateinit var getPartnerUseCase: GetPartnerUseCase
    private lateinit var partnerRepository: PartnerRepository
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        sessionRepository = getKoin().get()
        getPartnerUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns partner for session user id`() = runTest {
        val userId = "user123"
        val partner = PartnerMockData.PARTNER_1

        everySuspend { sessionRepository.getSessionUserId() } returns userId
        everySuspend { partnerRepository.getPartner(userId) } returns Result.success(partner)

        val result = getPartnerUseCase()

        assertTrue(result.isSuccess)
        assertEquals(partner, result.getOrNull())
        verifySuspend { sessionRepository.getSessionUserId() }
        verifySuspend { partnerRepository.getPartner(userId) }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val userId = "user123"
        val exception = Exception("Partner not found")

        everySuspend { sessionRepository.getSessionUserId() } returns userId
        everySuspend { partnerRepository.getPartner(userId) } returns Result.failure(exception)

        val result = getPartnerUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { partnerRepository.getPartner(userId) }
    }
}
