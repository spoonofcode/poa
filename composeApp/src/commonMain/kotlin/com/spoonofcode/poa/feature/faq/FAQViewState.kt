package com.spoonofcode.poa.feature.faq

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class FAQViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val faqItems: List<FAQItem> = exampleFAQItems,
) : BaseViewState(screenState)

data class FAQItem(
    val question: String,
    val answer: String,
)

private val exampleFAQItems = listOf(
    FAQItem(
        "What is POA?",
        "POA is an app for managing partners, products, and dojo-related systems."
    ),
    FAQItem(
        "How does the notification system work?",
        "Notifications are powered by Firebase Cloud Messaging (FCM)."
    ),
    FAQItem(
        "Does the app work offline?",
        "Yes, some data can be cached locally for offline access."
    ),
    FAQItem(
        "Does it support dark mode?",
        "Of course — full integration with the Material3 Dark Theme."
    ),
)