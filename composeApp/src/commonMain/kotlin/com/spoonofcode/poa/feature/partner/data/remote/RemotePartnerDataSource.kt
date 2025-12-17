package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.feature.partner.domain.PartnerDataSource
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RemotePartnerDataSource : RemoteBaseDataSource(
    collectionName = "partners",
), PartnerDataSource {
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