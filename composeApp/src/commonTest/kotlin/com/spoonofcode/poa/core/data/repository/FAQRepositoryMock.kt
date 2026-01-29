package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.FAQMockData.FAQS
import com.spoonofcode.feature.faq.domain.FAQDataSource
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun faqRepositoryMock() = mock<FAQDataSource> {
    everySuspend { readAll() } returns Result.success(FAQS)
}