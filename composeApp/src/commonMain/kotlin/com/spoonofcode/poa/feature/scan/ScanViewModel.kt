package com.spoonofcode.poa.feature.scan

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetProductByTagIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByTagIdUseCase: GetProductByTagIdUseCase,
) : BaseViewModel<ScanViewState, ScanViewAction>(ScanViewState()) {

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.CheckProduct -> checkProduct(action.tagId)
        }
    }

    private fun checkProduct(tagId: String) {
        viewModelScope.launch {
            showLoadingView()
            getProductByTagIdUseCase(tagId = tagId)
                .onSuccess {
                    viewModelNavigator.push(
                        ProductDetailsScreen(
                            productId = it.id,
                            hasOwnerUser = it.ownerUserId != null,
                        )
                    )
                }
                .onFailure {
                    showErrorView()
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
}