package com.spoonofcode.feature.product.presentation.details

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.model.ProductStatus

@Immutable
internal data class ProductDetailsViewState(
    val product: Product? = null,
    val productStatus: ProductStatus = ProductStatus.UNASSIGNED,
    val isDeleteProductDialogVisible: Boolean = false,
) : BaseViewState() {
    val hasOwnerUser: Boolean = product?.ownerEmail != null
}