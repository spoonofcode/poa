package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.profile.data.model.User
import com.spoonofcode.poa.feature.partner.domain.PartnerDataSource

class GetPartnerUsersUseCase(
    private val partnerDataSource: PartnerDataSource,
) {
    suspend operator fun invoke(partnerId: String): Result<List<User>> =
        partnerDataSource.readPartnerUsers(partnerId = partnerId)
}

