package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import io.ktor.http.HttpMethod

internal class RemotePartnerCategoryDataSource :
    RemoteBaseDataSource(
        collectionName = "api/partnerCategories",
    ) {
    suspend fun readAll(): Result<List<String>> =
        doRequest(
            method = HttpMethod.Get,
        )
}
