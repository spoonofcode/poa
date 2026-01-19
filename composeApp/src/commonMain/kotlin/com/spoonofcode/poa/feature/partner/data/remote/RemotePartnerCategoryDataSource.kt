package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.data.model.PartnerCategoryResponse
import io.ktor.http.HttpMethod

class RemotePartnerCategoryDataSource : RemoteBaseDataSource(
    collectionName = "api/partnerCategories",
) {
    suspend fun readAll(): Result<List<PartnerCategoryResponse>> =
        doRequest<List<PartnerCategoryResponse>>(
            method = HttpMethod.Get,
        )
}