package com.spoonofcode.poa.feature.faq.data.mappers

import com.spoonofcode.poa.feature.faq.data.remote.dto.FAQDto
import com.spoonofcode.poa.feature.faq.domain.model.FAQ

fun FAQDto.toFAQ(): FAQ {
    return FAQ(
        question = question,
        answer = answer,
    )
}