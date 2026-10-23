package com.spoonofcode.feature.faq.presentation

import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState

internal class FAQViewModel :
    BaseViewModel<FAQViewState, FAQViewAction, Nothing>(
        initialViewState = ViewState.Content(FAQViewState()),
    )
