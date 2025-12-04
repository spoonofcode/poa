package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.PartnerCategory
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class PartnerCategoryRepository : BaseRepository(
    collectionName = "partnerCategories",
) {
    suspend fun readAll(): Result<List<PartnerCategory>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<PartnerCategory>>(),
    )
}