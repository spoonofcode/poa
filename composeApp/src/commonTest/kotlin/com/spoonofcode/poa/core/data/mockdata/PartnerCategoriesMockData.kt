package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.feature.partner.domain.model.PartnerCategory

object PartnerCategoriesMockData {
    val PARTNER_CATEGORY_1 = PartnerCategory(
        id = "550e8400-e29b-41d4-a716-446655440001",
        name = "Sport",
    )
    val PARTNER_CATEGORY_2 = PartnerCategory(
        id = "550e8400-e29b-41d4-a716-446655440002",
        name = "Beauty",
    )
    val PARTNER_CATEGORY_3 = PartnerCategory(
        id = "550e8400-e29b-41d4-a716-446655440003",
        name = "Tech",
    )
    val PARTNER_CATEGORIES = listOf(PARTNER_CATEGORY_1, PARTNER_CATEGORY_3)
}