package com.spoonofcode.poa.feature.faq.domain.usecase

import com.spoonofcode.poa.feature.faq.domain.FAQDataSource
import com.spoonofcode.poa.feature.faq.domain.model.FAQ

class GetFAQUseCase(
    private val faqDataSource: FAQDataSource,
) {
    suspend operator fun invoke(): Result<List<FAQ>> = faqDataSource.readAll()
}