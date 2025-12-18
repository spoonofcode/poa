package com.spoonofcode.poa.feature.partner.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.data.mappers.toPartnerCategory
import com.spoonofcode.poa.feature.partner.data.model.PartnerCategoryResponse
import com.spoonofcode.poa.feature.partner.domain.PartnerCategoryDataSource
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RemotePartnerCategoryDataSource : RemoteBaseDataSource(
    collectionName = "partnerCategories",
), PartnerCategoryDataSource {
    override suspend fun readAll(): Result<List<PartnerCategory>> =
        doRequest<List<PartnerCategoryResponse>>(
            method = HttpMethod.Get,
            responseTypeInfo = typeInfo<List<PartnerCategoryResponse>>(),
        ).map { response ->
            response.map {
                it.toPartnerCategory()
            }
        }
}