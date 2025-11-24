package com.spoonofcode.poa.feature.faq

sealed interface FAQViewAction {
    data object InitView : FAQViewAction
}