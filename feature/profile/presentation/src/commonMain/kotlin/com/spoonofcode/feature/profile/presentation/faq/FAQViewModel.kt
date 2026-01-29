package com.spoonofcode.feature.profile.presentation.faq

import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState

internal class FAQViewModel() : BaseViewModel<FAQViewState, FAQViewAction>(
    initialViewState = FAQViewState(),
    initialScreenState = ScreenState.CONTENT
)