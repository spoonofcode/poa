package com.spoonofcode.poa.feature.faq.domain

import com.spoonofcode.poa.feature.faq.domain.model.FAQ

interface FAQDataSource {
    suspend fun readAll(): Result<List<FAQ>>
}