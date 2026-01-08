package com.spoonofcode.poa.feature.product.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.usecase.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.poa.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.poa.feature.product.presentation.edit.ProductEditScreen
import kotlinx.coroutines.launch

internal class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToUserUseCase: AddProductToUserUseCase,
    private val deleteProductFromUserUseCase: DeleteProductFromUserUseCase,
    private val subscribeUserTopicsUseCase: SubscribeUserTopicsUseCase,
) : BaseViewModel<ProductDetailsViewState, ProductDetailsViewAction>(ProductDetailsViewState()) {

    override fun onAction(action: ProductDetailsViewAction) {
        when (action) {
            ProductDetailsViewAction.AddProductToUser -> addProductToUser()
            ProductDetailsViewAction.DeleteProductFromUser -> deleteProductFromUser()
            ProductDetailsViewAction.EditProduct -> editProduct()
            is ProductDetailsViewAction.InitView -> initView(productId = action.productId)
        }
    }

    private fun initView(productId: String) {
        showLoadingView()
        viewModelScope.launch {
            getProductByIdUseCase(productId = productId)
                .onSuccess {
                    showContentView {
                        copy(
                            product = it
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun addProductToUser() {
        viewModelScope.launch {
            val currentState = currentState()
            addProductToUserUseCase(
                productId = currentState.product!!.id
            )
                .onSuccess {
                    //                subscribeUserTopicsUseCase()
                    viewModelNavigator.pop()
                }
                .onFailure {
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

    private fun deleteProductFromUser() {
        viewModelScope.launch {
            showLoadingView()
            val currentState = currentState()
            deleteProductFromUserUseCase(
                productId = currentState.product!!.id
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