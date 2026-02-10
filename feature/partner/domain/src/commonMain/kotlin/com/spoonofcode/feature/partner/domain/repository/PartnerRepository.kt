package com.spoonofcode.feature.partner.domain.repository

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.feature.partner.domain.model.PartnerUser

interface PartnerRepository {
    suspend fun getPartner(partnerId: String): Result<Partner>
    suspend fun updatePartner(
        partnerId: String,
        name: String,
        partnerCategory: String,
        email: String? = null,
        description: String? = null,
        imageLink: String? = null,
        websiteLink: String? = null,
        instagramLink: String? = null,
        facebookLink: String? = null,
        youtubeLink: String? = null,
        xLink: String? = null,
    ): Result<Unit>

    suspend fun getPartners(): Result<List<Partner>>
    suspend fun getPartnerUsers(partnerId: String): Result<List<PartnerUser>>
    suspend fun getCategories(): Result<List<PartnerCategory>>
}