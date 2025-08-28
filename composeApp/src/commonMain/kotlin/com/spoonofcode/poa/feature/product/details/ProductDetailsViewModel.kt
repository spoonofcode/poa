package com.spoonofcode.poa.feature.product.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.DeleteProductUseCase
import com.spoonofcode.poa.core.domain.product.EditProductUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.domain.user.AddProductToUserUseCase
import com.spoonofcode.poa.core.model.ProductRequest
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToUserUseCase: AddProductToUserUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val editProductUseCase: EditProductUseCase,
) : BaseViewModel<ProductDetailsViewState>(ProductDetailsViewState()) {

    fun initView(productId: Int) {
        viewModelScope.launch {
            showLoadingView()
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

    fun addProduct() {
        viewModelScope.launch {
            try {
                val currentState = currentState()
                addProductToUserUseCase(
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

    fun editProduct() {
        viewModelScope.launch {
            try {
                val currentState = currentState()
                editProductUseCase(
                    productId = currentState.product!!.id,
                    productRequest = ProductRequest(
                        name = currentState.product.name,
                        description = currentState.product.description,
                        imageLink = currentState.product.imageLink,
                        collectionName = currentState.product.collectionName,
                        websiteLink = currentState.product.websiteLink,
                        customLink = currentState.product.customLink,
                        seriesId = currentState.product.seriesId,
                        tagId = currentState.product.tagId,
                        ownerUserId = currentState.product.ownerUser!!.id
                    )
                )
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    fun deleteProduct() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                deleteProductUseCase(
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