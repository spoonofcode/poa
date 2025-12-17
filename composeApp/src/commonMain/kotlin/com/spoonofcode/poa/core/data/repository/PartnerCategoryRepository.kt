package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.core.model.PartnerCategory
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface PartnerCategoryRepository {
    suspend fun readAll(): Result<List<PartnerCategory>>
}

class PartnerCategoryRemoteDataSource : RemoteBaseDataSource(
    collectionName = "partnerCategories",
), PartnerCategoryRepository {
    override suspend fun readAll(): Result<List<PartnerCategory>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<PartnerCategory>>(),
    )
}