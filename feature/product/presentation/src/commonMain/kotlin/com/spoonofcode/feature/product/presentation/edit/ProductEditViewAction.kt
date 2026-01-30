package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductEditViewAction : BaseViewAction {
    data class ChangeCustomLink(val link: String) : ProductEditViewAction
    data class InitView(val productId: String) : ProductEditViewAction
    data object SaveChanges : ProductEditViewAction
}