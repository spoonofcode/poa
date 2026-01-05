package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.data.model.PartnerRequest
import com.spoonofcode.poa.feature.partner.data.model.PartnerResponse
import com.spoonofcode.poa.feature.profile.data.model.User
import io.ktor.http.HttpMethod

class RemotePartnerDataSource : RemoteBaseDataSource(
    collectionName = "partners",
) {
    suspend fun read(partnerId: String): Result<PartnerResponse> = doRequest<PartnerResponse>(
        urlPostfixPath = partnerId,
        method = HttpMethod.Get,
    )

    suspend fun update(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Result<Boolean> = doRequest<Boolean>(
        urlPostfixPath = partnerId,
        method = HttpMethod.Put,
        requestBody = partnerRequest,
    )

    suspend fun readAll(): Result<List<PartnerResponse>> = doRequest<List<PartnerResponse>>(
        method = HttpMethod.Get,
    )

    suspend fun readPartnerUsers(partnerId: String): Result<List<User>> = doRequest(
        urlPostfixPath = "/$partnerId/users",
        method = HttpMethod.Get,
    )
}