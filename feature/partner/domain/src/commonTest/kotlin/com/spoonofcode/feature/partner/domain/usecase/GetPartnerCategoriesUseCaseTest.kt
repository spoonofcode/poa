package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.data.test.PartnerCategoriesMockData
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

class GetPartnerCategoriesUseCaseTest : BaseTest() {
    private lateinit var getPartnerCategoriesUseCase: GetPartnerCategoriesUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        getPartnerCategoriesUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns partner categories from repository`() =
        runTest {
            val categories = PartnerCategoriesMockData.PARTNER_CATEGORIES
            everySuspend { partnerRepository.getCategories() } returns Result.success(categories)

            val result = getPartnerCategoriesUseCase()

            assertTrue(result.isSuccess)
            assertEquals(categories, result.getOrNull())
            verifySuspend { partnerRepository.getCategories() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get categories")
            everySuspend { partnerRepository.getCategories() } returns Result.failure(exception)

            val result = getPartnerCategoriesUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { partnerRepository.getCategories() }
        }
}
