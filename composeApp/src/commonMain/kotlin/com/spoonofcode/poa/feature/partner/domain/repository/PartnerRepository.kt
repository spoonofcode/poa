package com.spoonofcode.poa.feature.partner.domain.repository

import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.poa.feature.profile.data.model.User

interface PartnerRepository {
    suspend fun read(partnerId: String): Result<Partner>
    suspend fun update(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean>
    suspend fun readAll(): Result<List<Partner>>
    suspend fun readPartnerUsers(partnerId: String): Result<List<User>>
    suspend fun readAllCategories(): Result<List<PartnerCategory>>
    suspend fun readUserPartners(userId: String): Result<List<Partner>>
}