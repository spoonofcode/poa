package com.spoonofcode.poa.feature.faq.data.mappers

import com.spoonofcode.poa.feature.faq.data.remote.model.FAQResponse
import com.spoonofcode.poa.feature.faq.domain.model.FAQ

fun FAQResponse.toFAQ(): FAQ {
    return FAQ(
        question = question,
        answer = answer,
    )
}