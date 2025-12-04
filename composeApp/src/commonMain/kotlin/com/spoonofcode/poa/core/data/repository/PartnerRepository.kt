package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class PartnerRepository : BaseRepository(
    collectionName = "partners",
) {
    suspend fun read(
        partnerId: String,
    ): Result<Partner> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Partner>()
    )

    suspend fun update(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Result<Unit> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Put,
        requestBody = partnerRequest,
        responseTypeInfo = typeInfo<Unit>()
    )

    suspend fun readAll(): Result<List<Partner>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<Partner>>(),
    )

    suspend fun readPartnerUsers(partnerId: String): Result<List<User>> = doRequest(
        urlPostfixPath = "$partnerId/users",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<User>>(),
    )
}
