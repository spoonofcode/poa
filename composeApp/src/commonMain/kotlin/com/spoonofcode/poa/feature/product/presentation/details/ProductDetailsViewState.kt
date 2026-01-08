package com.spoonofcode.poa.feature.product.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.feature.product.domain.model.Product

@Immutable
internal data class ProductDetailsViewState(
    val product: Product? = null,
) : BaseViewState()