package com.spoonofcode.feature.product.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.poa.feature.product.domain.model.Product

@Immutable
internal data class ProductDetailsViewState(
    val product: Product? = null,
) : BaseViewState()