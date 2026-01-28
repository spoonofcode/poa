package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import io.ktor.http.HttpMethod

class RemotePartnerCategoryDataSource : RemoteBaseDataSource(
    collectionName = "api/partnerCategories",
) {
    suspend fun readAll(): Result<List<String>> =
        doRequest(
            method = HttpMethod.Get,
        )
}