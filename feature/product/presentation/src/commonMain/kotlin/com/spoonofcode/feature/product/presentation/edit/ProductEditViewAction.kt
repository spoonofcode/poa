package com.spoonofcode.feature.product.presentation.edit

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductEditViewAction : BaseViewAction {
    data class ChangeCustomLink(
        val link: String,
    ) : ProductEditViewAction

    data class ChangeRedirectLink(
        val link: String,
    ) : ProductEditViewAction

    data object SaveChanges : ProductEditViewAction
}
