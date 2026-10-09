package com.spoonofcode.feature.partner.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.Partner

@Immutable
internal data class PartnerEditViewState(
    val partner: Partner,
    val isSaveChangesButtonLoading: Boolean = false,
) : BaseViewState() {
    val isViewEnable: Boolean
        get() = !isSaveChangesButtonLoading

    val isSaveChangesButtonEnabled: Boolean
        get() = partner.name.isNotEmpty()
}