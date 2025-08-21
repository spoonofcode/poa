package com.spoonofcode.poa.feature.notification.edit

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class NotificationEditViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val seriesIds: List<String> = emptyList(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
) : BaseViewState(screenState)