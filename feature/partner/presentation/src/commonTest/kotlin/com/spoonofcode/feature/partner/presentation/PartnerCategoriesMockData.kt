package com.spoonofcode.feature.partner.presentation

import com.spoonofcode.feature.partner.domain.model.PartnerCategory

object PartnerCategoriesMockData {
    val PARTNER_CATEGORY_1 = PartnerCategory(
        name = "Sport",
    )
    val PARTNER_CATEGORY_2 = PartnerCategory(
        name = "Beauty",
    )
    val PARTNER_CATEGORY_3 = PartnerCategory(
        name = "Tech",
    )
    val PARTNER_CATEGORIES = listOf(PARTNER_CATEGORY_1, PARTNER_CATEGORY_3)
}