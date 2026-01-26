package com.spoonofcode.poa.feature.faq.presentation

import com.spoonofcode.poa.core.ui.base.BaseViewModel
import com.spoonofcode.poa.core.ui.base.ScreenState

internal class FAQViewModel() : BaseViewModel<FAQViewState, FAQViewAction>(
    initialViewState = FAQViewState(),
    initialScreenState = ScreenState.CONTENT
)