package com.spoonofcode.poa.feature.faq.presentation

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface FAQViewAction: BaseViewAction {
    data object InitView : FAQViewAction
}