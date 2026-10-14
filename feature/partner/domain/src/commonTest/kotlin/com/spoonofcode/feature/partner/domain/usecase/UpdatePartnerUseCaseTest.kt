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

class UpdatePartnerUseCaseTest : BaseTest() {

    private lateinit var updatePartnerUseCase: UpdatePartnerUseCase
    private lateinit var partnerRepository: PartnerRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        partnerRepository = getKoin().get()
        updatePartnerUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke calls repository updatePartner with partner properties`() = runTest {
        val partner = PartnerMockData.PARTNER_1
        everySuspend {
            partnerRepository.updatePartner(
                partner.id,
                name = partner.name,
                partnerCategory = partner.partnerCategory,
                email = partner.email,
                description = partner.description,
                imageLink = partner.imageLink,
                websiteLink = partner.websiteLink,
                instagramLink = partner.instagramLink,
                facebookLink = partner.facebookLink,
                youtubeLink = partner.youtubeLink,
                xLink = partner.xLink
            )
        } returns Result.success(Unit)

        val result = updatePartnerUseCase(partner)

        assertTrue(result.isSuccess)
        verifySuspend {
            partnerRepository.updatePartner(
                partner.id,
                name = partner.name,
                partnerCategory = partner.partnerCategory,
                email = partner.email,
                description = partner.description,
                imageLink = partner.imageLink,
                websiteLink = partner.websiteLink,
                instagramLink = partner.instagramLink,
                facebookLink = partner.facebookLink,
                youtubeLink = partner.youtubeLink,
                xLink = partner.xLink
            )
        }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val partner = PartnerMockData.PARTNER_1
        val exception = Exception("Update failed")
        everySuspend {
            partnerRepository.updatePartner(
                partner.id,
                name = partner.name,
                partnerCategory = partner.partnerCategory,
                email = partner.email,
                description = partner.description,
                imageLink = partner.imageLink,
                websiteLink = partner.websiteLink,
                instagramLink = partner.instagramLink,
                facebookLink = partner.facebookLink,
                youtubeLink = partner.youtubeLink,
                xLink = partner.xLink
            )
        } returns Result.failure(exception)

        val result = updatePartnerUseCase(partner)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
