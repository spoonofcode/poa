package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.model.User
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface PartnerRepository {
    suspend fun read(partnerId: String): Result<Partner>
    suspend fun update(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean>
    suspend fun readAll(): Result<List<Partner>>
    suspend fun readPartnerUsers(partnerId: String): Result<List<User>>
}

class PartnerRepositoryImpl : BaseRepository(
    collectionName = "partners",
), PartnerRepository {
    override suspend fun read(partnerId: String): Result<Partner> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Partner>()
    )

    override suspend fun update(partnerId: String, partnerRequest: PartnerRequest): Result<Boolean> = doRequest<Boolean>(
        urlPostfixPath = partnerId,
        method = HttpMethod.Put,
        requestBody = partnerRequest,
        responseTypeInfo = typeInfo<Unit>()
    ).map { true }

    override suspend fun readAll(): Result<List<Partner>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<Partner>>(),
    )

    override suspend fun readPartnerUsers(partnerId: String): Result<List<User>> = doRequest(
        urlPostfixPath = "$partnerId/users",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<User>>(),
    )
}