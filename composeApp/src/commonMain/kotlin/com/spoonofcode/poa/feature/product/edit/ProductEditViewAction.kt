package com.spoonofcode.poa.feature.product.edit

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProductEditViewAction : BaseViewAction {
    data class ChangeCustomLink(val link: String) : ProductEditViewAction
    data class InitView(val productId: String) : ProductEditViewAction
    data object SaveChanges : ProductEditViewAction
}