package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.data.model.PartnerResponse
import io.ktor.http.HttpMethod

class RemoteUserPartnerDataSource : RemoteBaseDataSource(
    collectionName = "users"
) {
    suspend fun readUserPartners(userId: String): Result<List<PartnerResponse>> = doRequest(
        urlPostfixPath = "$userId/partners",
        method = HttpMethod.Get,
    )
}