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

class GetPartnerCustomersUseCaseTest : BaseTest() {
    private lateinit var getPartnerCustomersUseCase: GetPartnerCustomersUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getPartnerCustomersUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns customers from repository`() =
        runTest {
            val customers = PartnerMockData.CUSTOMERS
            everySuspend { partnerRepository.getPartnerCustomers() } returns Result.success(customers)

            val result = getPartnerCustomersUseCase()

            assertTrue(result.isSuccess)
            assertEquals(customers, result.getOrNull())
            verifySuspend { partnerRepository.getPartnerCustomers() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get customers")
            everySuspend { partnerRepository.getPartnerCustomers() } returns Result.failure(exception)

            val result = getPartnerCustomersUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { partnerRepository.getPartnerCustomers() }
        }
}
