package com.spoonofcode.feature.product.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.product.domain.model.Product

@Immutable
internal data class ProductEditViewState(
    val product: Product,
    val isSaveChangesButtonLoading: Boolean = false,
) : BaseViewState() {
    val isViewEnable: Boolean
        get() = !isSaveChangesButtonLoading

    val isSaveChangesButtonEnabled: Boolean
        get() = product.customLink?.isNotEmpty() == true || product.redirectLink?.isNotEmpty() == true
}
