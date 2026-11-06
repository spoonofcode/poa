package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.data.test.PartnerMockData
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPartnersFilteredByTextUseCaseTest : BaseTest() {
    private lateinit var getPartnersFilteredByTextUseCase: GetPartnersFilteredByTextUseCase

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        getPartnersFilteredByTextUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns partners matching search text ignore case`() =
        runTest {
            val partners = PartnerMockData.PARTNERS
            val result = getPartnersFilteredByTextUseCase("beauty", partners)

            assertEquals(1, result.size)
            assertEquals(PartnerMockData.PARTNER_1, result.first())
        }

    @Test
    fun `invoke returns empty list when no partner matches`() =
        runTest {
            val partners = PartnerMockData.PARTNERS
            val result = getPartnersFilteredByTextUseCase("nonexisting", partners)

            assertTrue(result.isEmpty())
        }
}
