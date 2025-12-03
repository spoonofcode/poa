package com.spoonofcode.poa.feature.product.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.user.product.AddProductToUserUseCase
import com.spoonofcode.poa.core.domain.user.SubscribeUserTopicsUseCase
import com.spoonofcode.poa.core.domain.user.product.DeleteProductFromUserUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.product.edit.ProductEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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
            try {
                val product = getProductByIdUseCase(productId = productId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        product = product
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    private fun addProductToUser() {
        viewModelScope.launch {
            try {
                val currentState = currentState()
                addProductToUserUseCase(
                    productId = currentState.product!!.id
                )
//                subscribeUserTopicsUseCase()
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
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
            try {
                val currentState = currentState()
                deleteProductFromUserUseCase(
                    productId = currentState.product!!.id
                )
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.LOADING
            )
        }
    }
}