package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository

class UpdatePartnerUseCase(
    private val partnerRepository: PartnerRepository,

    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(partner: Partner): Result<Unit> {
        val currentUserId = sessionManager.getSessionUserId()

        val partnerRequest = PartnerRequest(
            userId = currentUserId,
            name = partner.name,
            email = partner.email,
            description = partner.description,
            imageLink = partner.imageLink,
            websiteLink = partner.websiteLink,
            instagramLink = partner.instagramLink,
            facebookLink = partner.facebookLink,
            youtubeLink = partner.youtubeLink,
            xLink = partner.xLink
        )

        return partnerRepository.update(partner.id, partnerRequest).map { }
    }
}