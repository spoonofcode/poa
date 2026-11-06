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

class GetNewPartnersUseCaseTest : BaseTest() {
    private lateinit var getNewPartnersUseCase: GetNewPartnersUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getNewPartnersUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns filtered and limited list of partners`() =
        runTest {
            val partners = PartnerMockData.PARTNERS
            everySuspend { partnerRepository.getPartners() } returns Result.success(partners)

            val result = getNewPartnersUseCase(limit = 1)

            assertTrue(result.isSuccess)
            assertEquals(1, result.getOrNull()?.size)
            verifySuspend { partnerRepository.getPartners() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get partners")
            everySuspend { partnerRepository.getPartners() } returns Result.failure(exception)

            val result = getNewPartnersUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { partnerRepository.getPartners() }
        }
}
