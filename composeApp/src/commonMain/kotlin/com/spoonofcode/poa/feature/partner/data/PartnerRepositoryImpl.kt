package com.spoonofcode.poa.feature.partner.data

import com.spoonofcode.poa.feature.partner.data.mappers.toPartner
import com.spoonofcode.poa.feature.partner.data.mappers.toPartnerCategory
import com.spoonofcode.poa.feature.partner.data.mappers.toPartnerUser
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.data.remote.RemotePartnerCategoryDataSource
import com.spoonofcode.poa.feature.partner.data.remote.RemotePartnerDataSource
import com.spoonofcode.poa.feature.partner.data.remote.RemoteUserPartnerDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository

class PartnerRepositoryImpl(
    private val remotePartnerDataSource: RemotePartnerDataSource,
    private val remotePartnerCategoryDataSource: RemotePartnerCategoryDataSource,
    private val remoteUserPartnerDataSource: RemoteUserPartnerDataSource,
) : PartnerRepository {

    override suspend fun getPartner(partnerId: String): Result<Partner> =
        remotePartnerDataSource.read(partnerId).map { it.toPartner() }

    override suspend fun updatePartner(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Result<Boolean> = remotePartnerDataSource.update(
        partnerId = partnerId,
        partnerRequest = partnerRequest,
    ).map { true }

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
                it.toPartnerCategory()
            }
        }

    override suspend fun getUserPartners(userId: String): Result<List<Partner>> {
        return remoteUserPartnerDataSource.readUserPartners(userId).map { response ->
            response.map {
                it.toPartner()
            }
        }
    }
}