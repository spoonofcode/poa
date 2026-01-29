package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductEditViewAction : BaseViewAction {
    data class ChangeCustomLink(val link: String) :
        com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction
    data class InitView(val productId: String) :
        com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction
    data object SaveChanges :
        com.spoonofcode.poa.feature.product.presentation.edit.ProductEditViewAction
}