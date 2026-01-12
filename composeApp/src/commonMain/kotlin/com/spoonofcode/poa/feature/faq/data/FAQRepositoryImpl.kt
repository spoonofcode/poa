package com.spoonofcode.poa.feature.faq.data

import com.spoonofcode.poa.feature.faq.data.mappers.toFAQ
import com.spoonofcode.poa.feature.faq.data.remote.RemoteFAQDataSource
import com.spoonofcode.poa.feature.faq.domain.model.FAQ
import com.spoonofcode.poa.feature.faq.domain.repository.FAQRepository

// TODO Set backend call request
class FAQRepositoryImpl(
    private val remoteFAQDataSource: RemoteFAQDataSource,
) : FAQRepository {
    override suspend fun readAll(): Result<List<FAQ>> = Result.success(mockedBackendFAQs)
//        remoteFAQDataSource.readAll()
//        .map { list ->
//            list.map { it.toFAQ() }
//        }
}

private val mockedBackendFAQs = listOf(
    FAQ(
        "What is POA?",
        "POA is an app for managing partners, products, and dojo-related systems."
    ),
    FAQ(
        "How does the notification system work?",
        "Notifications are powered by Firebase Cloud Messaging (FCM)."
    ),
    FAQ(
        "Does the app work offline?",
        "Yes, some data can be cached locally for offline access."
    ),
    FAQ(
        "Does it support dark mode?",
        "Of course — full integration with the Material3 Dark Theme."
    ),
)