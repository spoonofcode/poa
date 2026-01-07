package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.data.model.PartnerResponse
import io.ktor.http.HttpMethod

class RemoteUserPartnerDataSource : RemoteBaseDataSource(
    collectionName = "users"
) {
    suspend fun readUserPartners(): Result<List<PartnerResponse>> = doRequest(
        urlPostfixPath = "partners",
        method = HttpMethod.Get,
    )
}