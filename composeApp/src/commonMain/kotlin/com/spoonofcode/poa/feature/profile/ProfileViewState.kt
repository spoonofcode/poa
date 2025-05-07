package com.spoonofcode.poa.feature.profile

import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProfileViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val profile: Profile? = null,
) : BaseViewState(
    isLoadingView = isLoadingView,
)