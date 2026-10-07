package com.spoonofcode.feature.product.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.product.domain.model.Product

@Immutable
internal data class ProductEditViewState(
    val product: Product,
) : BaseViewState() {
    val isSaveChangesButtonEnabled: Boolean
        get() = product.customLink?.isNotEmpty() ?: false
}