package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class UpdatePartnerUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(partner: Partner): Result<Unit> {

        return partnerRepository.updatePartner(
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
        ).map { }
    }
}