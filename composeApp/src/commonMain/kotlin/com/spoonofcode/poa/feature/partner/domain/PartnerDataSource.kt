package com.spoonofcode.poa.feature.partner.domain

import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.core.model.User

interface PartnerDataSource {
    suspend fun read(partnerId: String): Result<Partner>
    suspend fun update(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean>
    suspend fun readAll(): Result<List<Partner>>
    suspend fun readPartnerUsers(partnerId: String): Result<List<User>>
}