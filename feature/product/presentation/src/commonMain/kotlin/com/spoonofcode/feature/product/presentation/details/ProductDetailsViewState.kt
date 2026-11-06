package com.spoonofcode.feature.product.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.model.ProductStatus

@Immutable
internal data class ProductDetailsViewState(
    val product: Product,
    val productStatus: ProductStatus,
    val isDeleteProductDialogVisible: Boolean = false,
    val isAddProductToProfileButtonLoading: Boolean = false,
    val isDeleteProductFromProfileButtonLoading: Boolean = false,
) : BaseViewState() {
    val isViewEnable: Boolean
        get() = !isAddProductToProfileButtonLoading && !isDeleteProductFromProfileButtonLoading
}
