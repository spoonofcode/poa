package com.spoonofcode.feature.product.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductStatusUseCase
import kotlinx.coroutines.launch

internal class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToUserUseCase: AddProductToUserUseCase,
    private val deleteProductFromUserUseCase: DeleteProductFromUserUseCase,
    private val getProductStatusUseCase: GetProductStatusUseCase,
) : BaseViewModel<ProductDetailsViewState, ProductDetailsViewAction, Nothing>(
    initialViewState = ViewState.Content(ProductDetailsViewState()),
) {
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
                    updateContentState {
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
            val product = requireNotNull(currentContentState().product)
            addProductToUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            ).onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun editProduct() {
        val productId = currentContentState().product!!.id
        viewModelScope.launch {
            routeResolver.resolve(ProductModuleRoute.ProductEdit(productId = productId))
        }
    }

    private fun deleteProductFromProfile() {
        updateContentState {
            copy(
                isDeleteProductDialogVisible = true,
            )
        }
    }

    private fun cancelDeleteProductFromProfile() {
        updateContentState {
            copy(
                isDeleteProductDialogVisible = false,
            )
        }
    }

    private fun confirmDeleteProductFromProfile() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentContentState().product)
            deleteProductFromUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            )
                .onSuccess {
                    viewModelNavigator.pop()
                }
                .onFailure {
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }
}