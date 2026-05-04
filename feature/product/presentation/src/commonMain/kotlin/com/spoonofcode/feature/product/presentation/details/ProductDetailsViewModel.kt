package com.spoonofcode.feature.product.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductStatusUseCase
import com.spoonofcode.feature.product.presentation.edit.ProductEditScreen
import kotlinx.coroutines.launch

internal class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToUserUseCase: AddProductToUserUseCase,
    private val deleteProductFromUserUseCase: DeleteProductFromUserUseCase,
    private val getProductStatusUseCase: GetProductStatusUseCase,
) : BaseViewModel<ProductDetailsViewState, ProductDetailsViewAction>(ProductDetailsViewState()) {

    override fun onAction(action: ProductDetailsViewAction) {
        when (action) {
            ProductDetailsViewAction.AddProductToProfile -> addProductToProfile()
            ProductDetailsViewAction.DeleteProductFromProfile -> deleteProductFromProfile()
            ProductDetailsViewAction.EditProduct -> editProduct()
            is ProductDetailsViewAction.InitView -> initView(productId = action.productId)
            ProductDetailsViewAction.CancelDeleteProductFromProfile -> cancelDeleteProductFromProfile()
            ProductDetailsViewAction.ConfirmDeleteProductFromProfile -> confirmDeleteProductFromProfile()
        }
    }

    private fun initView(productId: String) {
        showLoadingView()
        viewModelScope.launch {
            getProductByIdUseCase(productId = productId)
                .onSuccess { product ->
                    val productStatus = getProductStatusUseCase(ownerEmail = product.ownerEmail)

                    showContentView {
                        copy(
                            product = product,
                            productStatus = productStatus,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun addProductToProfile() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentState().product)
            addProductToUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showErrorSnackbar(it)
            }
        }
    }

    private fun editProduct() {
        val productId = currentState().product!!.id
        viewModelScope.launch {
            viewModelNavigator.push(ProductEditScreen(productId = productId))
        }
    }

    private fun deleteProductFromProfile() {
        updateState {
            copy(
                isDeleteProductDialogVisible = true,
            )
        }
    }

    private fun cancelDeleteProductFromProfile() {
        updateState {
            copy(
                isDeleteProductDialogVisible = false,
            )
        }
    }

    private fun confirmDeleteProductFromProfile() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentState().product)
            deleteProductFromUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            )
                .onSuccess {
                    viewModelNavigator.pop()
                }
                .onFailure {
                    showErrorSnackbar(it)
                }
        }
    }
}