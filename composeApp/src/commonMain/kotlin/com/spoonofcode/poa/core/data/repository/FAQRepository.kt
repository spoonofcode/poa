package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.FAQ
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class FAQRepository : BaseRepository(
    collectionName = "faqs",
) {
    suspend fun readAll(): List<FAQ> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<FAQ>>(),
    )
}