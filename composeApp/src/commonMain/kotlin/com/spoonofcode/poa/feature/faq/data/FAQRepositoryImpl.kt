package com.spoonofcode.poa.feature.faq.data

import com.spoonofcode.poa.feature.faq.data.mappers.toFAQ
import com.spoonofcode.poa.feature.faq.data.remote.RemoteFAQDataSource
import com.spoonofcode.poa.feature.faq.domain.model.FAQ
import com.spoonofcode.poa.feature.faq.domain.repository.FAQRepository

class FAQRepositoryImpl(
    private val remoteFAQDataSource: RemoteFAQDataSource,
) : FAQRepository {
    override suspend fun readAll(): Result<List<FAQ>> = remoteFAQDataSource.readAll()
        .map { list ->
            list.map { it.toFAQ() }
        }
}