package com.spoonofcode.feature.partner.data

import com.spoonofcode.feature.partner.data.mappers.toPartner
import com.spoonofcode.feature.partner.data.mappers.toPartnerUser
import com.spoonofcode.feature.partner.data.remote.RemotePartnerCategoryDataSource
import com.spoonofcode.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.feature.partner.domain.model.PartnerUser
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class PartnerRepositoryImpl(
    private val remotePartnerDataSource: RemotePartnerDataSource,
    private val remotePartnerCategoryDataSource: RemotePartnerCategoryDataSource,
) : PartnerRepository {

    override suspend fun getPartner(partnerId: String): Result<Partner> =
        remotePartnerDataSource.read(partnerId).map { it.toPartner() }

    override suspend fun updatePartner(
        partnerId: String,
        name: String,
        partnerCategory: String,
        email: String?,
        description: String?,
        imageLink: String?,
        websiteLink: String?,
        instagramLink: String?,
        facebookLink: String?,
        youtubeLink: String?,
        xLink: String?
    ): Result<Unit> = remotePartnerDataSource.update(
        partnerId = partnerId,
        name = name,
        partnerCategory = partnerCategory,
        email = email,
        description = description,
        imageLink = imageLink,
        websiteLink = websiteLink,
        instagramLink = instagramLink,
        facebookLink = facebookLink,
        youtubeLink = youtubeLink,
        xLink = xLink,
    )

    override suspend fun getPartners(): Result<List<Partner>> =
        remotePartnerDataSource.readAll().map { response ->
            response.map { it.toPartner() }
        }

    override suspend fun getPartnerUsers(partnerId: String): Result<List<PartnerUser>> =
        remotePartnerDataSource.readPartnerUsers(partnerId).map { response ->
            response.map { it.toPartnerUser() }
        }

    override suspend fun getCategories(): Result<List<PartnerCategory>> =
        remotePartnerCategoryDataSource.readAll().map { response ->
            response.map {
                PartnerCategory(name = it)
            }
        }
}