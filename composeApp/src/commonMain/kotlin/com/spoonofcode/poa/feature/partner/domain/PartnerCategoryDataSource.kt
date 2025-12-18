package com.spoonofcode.poa.feature.partner.domain

import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory

interface PartnerCategoryDataSource {
    suspend fun readAll(): Result<List<PartnerCategory>>
}