package com.spoonofcode.feature.partner.domain.usecase

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

class GetPartnerByIdUseCaseTest : BaseTest() {

    private lateinit var getPartnerByIdUseCase: GetPartnerByIdUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getPartnerByIdUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns partner when repository succeeds`() = runTest {
        val partnerId = PartnerMockData.PARTNER_1.id
        everySuspend { partnerRepository.getPartner(partnerId) } returns Result.success(PartnerMockData.PARTNER_1)

        val result = getPartnerByIdUseCase(partnerId)

        assertTrue(result.isSuccess)
        assertEquals(PartnerMockData.PARTNER_1, result.getOrNull())
        verifySuspend { partnerRepository.getPartner(partnerId) }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val partnerId = "invalid_id"
        val exception = Exception("Partner not found")
        everySuspend { partnerRepository.getPartner(partnerId) } returns Result.failure(exception)

        val result = getPartnerByIdUseCase(partnerId)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { partnerRepository.getPartner(partnerId) }
    }
}
