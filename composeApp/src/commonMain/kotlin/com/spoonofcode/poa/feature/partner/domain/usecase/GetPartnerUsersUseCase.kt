package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.poa.feature.profile.data.model.User

class GetPartnerUsersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(partnerId: String): Result<List<User>> =
        partnerRepository.readPartnerUsers(partnerId = partnerId)
}

