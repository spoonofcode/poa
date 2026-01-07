package com.spoonofcode.poa.feature.faq.domain.usecase

import com.spoonofcode.poa.feature.faq.domain.model.FAQ
import com.spoonofcode.poa.feature.faq.domain.repository.FAQRepository

class GetFAQUseCase(
    private val faqRepository: FAQRepository,
) {
    suspend operator fun invoke(): Result<List<FAQ>> = faqRepository.readAll()
}