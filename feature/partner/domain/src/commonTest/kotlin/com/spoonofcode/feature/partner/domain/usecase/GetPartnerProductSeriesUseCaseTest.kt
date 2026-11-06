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

class GetPartnerProductSeriesUseCaseTest : BaseTest() {
    private lateinit var getPartnerProductSeriesUseCase: GetPartnerProductSeriesUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getPartnerProductSeriesUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns product series list from repository`() =
        runTest {
            val series = PartnerMockData.PRODUCT_SERIES_LIST
            everySuspend { partnerRepository.getPartnerSeries() } returns Result.success(series)

            val result = getPartnerProductSeriesUseCase()

            assertTrue(result.isSuccess)
            assertEquals(series, result.getOrNull())
            verifySuspend { partnerRepository.getPartnerSeries() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get product series")
            everySuspend { partnerRepository.getPartnerSeries() } returns Result.failure(exception)

            val result = getPartnerProductSeriesUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { partnerRepository.getPartnerSeries() }
        }
}
