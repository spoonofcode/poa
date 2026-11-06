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

class GetPartnersUseCaseTest : BaseTest() {
    private lateinit var getPartnersUseCase: GetPartnersUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getPartnersUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns partners filtering out those without imageLink`() =
        runTest {
            val partners =
                listOf(
                    PartnerMockData.PARTNER_1,
                    PartnerMockData.PARTNER_2.copy(imageLink = null),
                )
            everySuspend { partnerRepository.getPartners() } returns Result.success(partners)

            val result = getPartnersUseCase()

            assertTrue(result.isSuccess)
            assertEquals(listOf(PartnerMockData.PARTNER_1), result.getOrNull())
            verifySuspend { partnerRepository.getPartners() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get partners")
            everySuspend { partnerRepository.getPartners() } returns Result.failure(exception)

            val result = getPartnersUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { partnerRepository.getPartners() }
        }
}
