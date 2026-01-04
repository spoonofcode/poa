package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.feature.faq.domain.model.FAQ
import com.spoonofcode.poa.feature.profile.data.model.User

object FAQMockData {
    val FAQS = listOf(
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
}