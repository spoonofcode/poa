package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.data.test.PartnerMockData
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPartnersFilteredByPartnerCategoryUseCaseTest : BaseTest() {
    private lateinit var getPartnersFilteredByPartnerCategoryUseCase: GetPartnersFilteredByPartnerCategoryUseCase

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        getPartnersFilteredByPartnerCategoryUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns all partners when categories set is empty`() =
        runTest {
            val partners = PartnerMockData.PARTNERS
            val result = getPartnersFilteredByPartnerCategoryUseCase(emptySet(), partners)

            assertEquals(partners, result)
        }

    @Test
    fun `invoke filters partners matching selected categories`() =
        runTest {
            val partners = PartnerMockData.PARTNERS
            val category1 = PartnerMockData.PARTNER_1.partnerCategory
            val result = getPartnersFilteredByPartnerCategoryUseCase(setOf(category1), partners)

            assertEquals(1, result.size)
            assertEquals(PartnerMockData.PARTNER_1, result.first())
        }
}
