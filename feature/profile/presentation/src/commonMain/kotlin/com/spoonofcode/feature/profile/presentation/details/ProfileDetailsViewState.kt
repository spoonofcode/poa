package com.spoonofcode.feature.profile.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.profile.domain.model.Profile

@Immutable
internal data class ProfileDetailsViewState(
    val profile: Profile,
    val partnerPanelVisible: Boolean,
    val isLogoutDialogVisible: Boolean = false,
) : BaseViewState()
