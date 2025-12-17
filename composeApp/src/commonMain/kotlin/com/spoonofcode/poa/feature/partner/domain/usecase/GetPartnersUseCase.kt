package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.PartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetPartnersUseCase(
    private val partnerDataSource: PartnerDataSource,
) {
    suspend operator fun invoke(): Result<List<Partner>> = partnerDataSource.readAll()
}

