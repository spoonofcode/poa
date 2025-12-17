package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.FAQMockData.FAQS
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun faqRepositoryMock() = mock<FAQRepository> {
    everySuspend { readAll() } returns Result.success(FAQS)
}