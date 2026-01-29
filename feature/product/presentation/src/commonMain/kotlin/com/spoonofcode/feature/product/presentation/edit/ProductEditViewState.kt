package com.spoonofcode.feature.product.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class ProductEditViewState(
    val product: Product? = null
) : BaseViewState()