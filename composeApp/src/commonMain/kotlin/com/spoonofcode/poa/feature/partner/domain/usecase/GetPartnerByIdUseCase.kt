package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.PartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetPartnerByIdUseCase(
    private val partnerDataSource: PartnerDataSource,
) {
    suspend operator fun invoke(
        partnerId: String
    ): Result<Partner> = partnerDataSource.read(partnerId = partnerId)
}