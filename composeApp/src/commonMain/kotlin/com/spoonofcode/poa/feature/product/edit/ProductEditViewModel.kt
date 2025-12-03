package com.spoonofcode.poa.feature.product.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.EditProductUseCase
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.model.ProductRequest
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductEditViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val editProductUseCase: EditProductUseCase,
) : BaseViewModel<ProductEditViewState, ProductEditViewAction>(ProductEditViewState()) {

    override fun onAction(action: ProductEditViewAction) {
        when (action) {
            is ProductEditViewAction.ChangeCustomLink -> changeCustomLink(action.link)
            ProductEditViewAction.EditProduct -> editProduct()
            is ProductEditViewAction.InitView -> initView(action.productId)
            ProductEditViewAction.SaveChanges -> saveChanges()
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

    private fun editProduct() {
        viewModelScope.launch {
            try {
                val product = requireNotNull(currentState().product)
                editProductUseCase(
                    productId = product.id,
                    productRequest = ProductRequest(
                        name = product.name,
                        ownerUserId = product.ownerUserId,
                        partnerId = product.partnerId,
                        description = product.description,
                        imageLink = product.imageLink,
                        videoLink = product.videoLink,
                        collectionName = product.collectionName,
                        websiteLink = product.websiteLink,
                        customLink = product.customLink,
                        seriesId = product.seriesId,
                        tagId = product.tagId,
                    )
                )
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun changeCustomLink(link: String) {
        val currentProduct = currentState().product
        updateState { copy(product = currentProduct?.copy(customLink = link)) }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            try {
                val product = requireNotNull(currentState().product)
                editProductUseCase(
                    productId = product.id,
                    productRequest = ProductRequest(
                        name = product.name,
                        ownerUserId = product.ownerUserId,
                        partnerId = product.partnerId,
                        description = product.description,
                        imageLink = product.imageLink,
                        videoLink = product.videoLink,
                        collectionName = product.collectionName,
                        websiteLink = product.websiteLink,
                        customLink = product.customLink,
                        seriesId = product.seriesId,
                        tagId = product.tagId,
                    )
                )
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
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
}