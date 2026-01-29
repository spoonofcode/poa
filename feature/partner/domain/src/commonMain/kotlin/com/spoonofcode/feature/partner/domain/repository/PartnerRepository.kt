package com.spoonofcode.feature.partner.domain.repository

//import com.spoonofcode.feature.partner.data.model.PartnerRequest
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.feature.partner.domain.model.PartnerUser

interface PartnerRepository {
    suspend fun getPartner(partnerId: String): Result<Partner>
    // TODO #109 Fix after migration to feature modules
//    suspend fun updatePartner(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean>
    suspend fun getPartners(): Result<List<Partner>>
    suspend fun getPartnerUsers(partnerId: String): Result<List<PartnerUser>>
    suspend fun getCategories(): Result<List<PartnerCategory>>
}