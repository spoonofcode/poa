package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.partner.data.model.PartnerRequest
import com.spoonofcode.feature.partner.data.model.PartnerResponse
import com.spoonofcode.feature.partner.data.model.PartnerUserResponse
import io.ktor.http.HttpMethod

class RemotePartnerDataSource : RemoteBaseDataSource(
    collectionName = "api/partners",
) {
    suspend fun read(partnerId: String): Result<PartnerResponse> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Get,
    )

    suspend fun update(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Result<Unit> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Put,
        requestBody = partnerRequest,
    )

    suspend fun readAll(): Result<List<PartnerResponse>> = doRequest(
        method = HttpMethod.Get,
    )

    suspend fun readPartnerUsers(partnerId: String): Result<List<PartnerUserResponse>> = doRequest(
        urlPostfixPath = "$partnerId/users",
        method = HttpMethod.Get,
    )
}