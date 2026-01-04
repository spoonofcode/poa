package com.spoonofcode.poa.feature.faq.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.faq.data.mappers.toFAQ
import com.spoonofcode.poa.feature.faq.data.remote.model.FAQResponse
import com.spoonofcode.poa.feature.faq.domain.FAQDataSource
import com.spoonofcode.poa.feature.faq.domain.model.FAQ
import io.ktor.http.HttpMethod

class RemoteFAQDataSource : RemoteBaseDataSource(
    collectionName = "faqs",
), FAQDataSource {
    override suspend fun readAll(): Result<List<FAQ>> = doRequest<List<FAQResponse>>(
        method = HttpMethod.Get,
    ).map { response ->
        response.map { it.toFAQ() }
    }
}