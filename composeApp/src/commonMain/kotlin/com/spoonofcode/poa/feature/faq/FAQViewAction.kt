package com.spoonofcode.poa.feature.faq

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface FAQViewAction: BaseViewAction {
    data object InitView : FAQViewAction
}