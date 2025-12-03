package com.spoonofcode.poa.core.domain.faq

import com.spoonofcode.poa.core.data.repository.FAQRepository
import com.spoonofcode.poa.core.model.FAQ

class GetFAQUseCase(
    private val faqRepository: FAQRepository,
) {
    suspend operator fun invoke(): List<FAQ> = faqRepository.readAll()
}