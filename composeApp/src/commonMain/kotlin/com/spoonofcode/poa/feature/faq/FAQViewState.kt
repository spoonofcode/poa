package com.spoonofcode.poa.feature.faq

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.FAQ
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class FAQViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val faqs: List<FAQ> = exampleFAQS,
) : BaseViewState(screenState)

private val exampleFAQS = listOf(
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