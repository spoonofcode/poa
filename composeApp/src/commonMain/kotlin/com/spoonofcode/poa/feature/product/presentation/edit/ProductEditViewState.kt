package com.spoonofcode.poa.feature.product.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class ProductEditViewState(
    val product: Product? = null
) : BaseViewState()