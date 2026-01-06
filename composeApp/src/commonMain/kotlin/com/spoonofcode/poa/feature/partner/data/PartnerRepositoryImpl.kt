package com.spoonofcode.poa.feature.partner.data

import com.spoonofcode.poa.feature.partner.data.mappers.toPartner
import com.spoonofcode.poa.feature.partner.data.mappers.toPartnerCategory
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.data.remote.RemotePartnerCategoryDataSource
import com.spoonofcode.poa.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.poa.feature.partner.data.remote.RemoteUserPartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository
import com.spoonofcode.poa.feature.profile.data.model.User

class PartnerRepositoryImpl(
    private val remotePartnerDataSource: RemotePartnerDataSource,
    private val remotePartnerCategoryDataSource: RemotePartnerCategoryDataSource,
    private val remoteUserPartnerDataSource: RemoteUserPartnerDataSource,
) : PartnerRepository {
    override suspend fun read(partnerId: String): Result<Partner> =
        remotePartnerDataSource.read(partnerId).map { response -> response.toPartner() }

    override suspend fun update(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Result<Boolean> = remotePartnerDataSource.update(
        partnerId = partnerId,
        partnerRequest = partnerRequest,
    ).map { true }

    override suspend fun readAll(): Result<List<Partner>> =
        remotePartnerDataSource.readAll().map { response ->
            response.map { it.toPartner() }
        }

    override suspend fun readPartnerUsers(partnerId: String): Result<List<User>> =
        remotePartnerDataSource.readPartnerUsers(partnerId)

    override suspend fun readAllCategories(): Result<List<PartnerCategory>> =
        remotePartnerCategoryDataSource.readAll().map { response ->
            response.map {
                it.toPartnerCategory()
            }
        }

    override suspend fun readUserPartners(userId: String): Result<List<Partner>> {
        return remoteUserPartnerDataSource.readUserPartners(userId).map { response ->
            response.map {
                it.toPartner()
            }
        }
    }
}