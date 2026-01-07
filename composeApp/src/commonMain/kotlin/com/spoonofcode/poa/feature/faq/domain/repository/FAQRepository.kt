package com.spoonofcode.poa.feature.faq.domain.repository

import com.spoonofcode.poa.feature.faq.domain.model.FAQ

interface FAQRepository {
    suspend fun readAll(): Result<List<FAQ>>
}