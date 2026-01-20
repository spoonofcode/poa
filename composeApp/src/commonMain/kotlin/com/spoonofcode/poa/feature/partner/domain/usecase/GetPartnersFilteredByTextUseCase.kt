package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetPartnersFilteredByTextUseCase {
    operator fun invoke(
        searchText: String,
        partners: List<Partner>,
    ): List<Partner> = partners.filter { partner ->
        partner.name.contains(searchText, ignoreCase = true)
    }
}

