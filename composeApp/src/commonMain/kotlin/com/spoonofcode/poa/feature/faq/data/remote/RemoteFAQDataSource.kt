package com.spoonofcode.poa.feature.faq.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.faq.data.remote.model.FAQResponse
import io.ktor.http.HttpMethod

class RemoteFAQDataSource : RemoteBaseDataSource(
    collectionName = "faqs",
) {
    suspend fun readAll() = doRequest<List<FAQResponse>>(
        method = HttpMethod.Get,
    )
}