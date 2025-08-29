package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.network.SessionManager

class UpdatePartnerUseCase(
    private val partnerRepository: PartnerRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(partner: Partner) {
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

        partnerRepository.update(partner.id, partnerRequest)
    }
}