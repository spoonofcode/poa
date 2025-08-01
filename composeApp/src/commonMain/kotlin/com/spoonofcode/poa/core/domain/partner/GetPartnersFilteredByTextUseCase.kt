package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.model.Partner

class GetPartnersFilteredByTextUseCase {
    operator fun invoke(
        searchText: String,
        products: List<Partner>,
    ): List<Partner> = products.filter { partner ->
        partner.name.contains(searchText, ignoreCase = true)
    }
}

