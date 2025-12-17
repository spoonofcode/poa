package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetPartnersFilteredByTextUseCase {
    operator fun invoke(
        searchText: String,
        products: List<Partner>,
    ): List<Partner> = products.filter { partner ->
        partner.name.contains(searchText, ignoreCase = true)
    }
}

