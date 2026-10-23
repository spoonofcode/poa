package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Partner

class GetPartnersFilteredByPartnerCategoryUseCase {
    operator fun invoke(
        partnerCategories: Set<String>,
        partners: List<Partner>,
    ): List<Partner> =
        if (partnerCategories.isEmpty()) {
            partners
        } else {
            partners.filter { partner ->
                partnerCategories.contains(partner.partnerCategory)
            }
        }
}
