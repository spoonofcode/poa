package com.spoonofcode.poa.feature.product.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProductEditViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val product: Product? = null
) : BaseViewState(screenState)