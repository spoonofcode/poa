package com.spoonofcode.poa.feature.notification.edit

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class NotificationEditViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val seriesIds: List<String> = emptyList(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
) : BaseViewState()