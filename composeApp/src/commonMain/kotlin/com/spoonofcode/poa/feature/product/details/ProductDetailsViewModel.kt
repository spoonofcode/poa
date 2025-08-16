package com.spoonofcode.poa.feature.product.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductDetailsViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
//    private val deleteProductUseCase: DeleteProductUseCase,
) : BaseViewModel<ProductDetailsViewState>(ProductDetailsViewState()) {

    fun initView(productId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val product = getProductByIdUseCase(productId = productId)
                updateState {
                    copy(
                        isLoadingView = false,
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

    fun editProduct() {
//        viewModelScope.launch {
//            viewModelNavigator.push(ProductInfoEditScreen(screenMode = ScreenMode.Edit(nftId = currentState().nft!!.id)))
//        }
    }

    fun deleteProduct() {
//        viewModelScope.launch {
//            showLoadingView()
//            try {
//                val currentState = currentState()
//                deleteProductUseCase(
//                    nftId = currentState.nft!!.id,
//                )
//                viewModelNavigator.pop()
//            } catch (ce: CancellationException) {
//                throw ce
//            } catch (e: Exception) {
//                showErrorSnackbar(e)
//            }
//        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}