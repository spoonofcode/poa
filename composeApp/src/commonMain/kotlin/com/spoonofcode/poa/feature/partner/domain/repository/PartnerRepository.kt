package com.spoonofcode.poa.feature.partner.domain.repository

import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser

interface PartnerRepository {
    suspend fun getPartner(partnerId: String): Result<Partner>
    suspend fun updatePartner(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean>
    suspend fun getPartners(): Result<List<Partner>>
    suspend fun getPartnerUsers(partnerId: String): Result<List<PartnerUser>>
    suspend fun getCategories(): Result<List<PartnerCategory>>
}