package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.FAQ
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface FAQRepository {
    suspend fun readAll(): Result<List<FAQ>>
}

class FAQRepositoryImpl : BaseRepository(
    collectionName = "faqs",
), FAQRepository {
    override suspend fun readAll(): Result<List<FAQ>> = doRequest(
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<FAQ>>(),
    )
}