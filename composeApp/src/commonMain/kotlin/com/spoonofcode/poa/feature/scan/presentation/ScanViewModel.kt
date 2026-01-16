package com.spoonofcode.poa.feature.scan.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.base.BaseViewModel
import com.spoonofcode.poa.core.ui.base.ScreenState
import com.spoonofcode.poa.feature.product.domain.usecase.GetProductByTagIdUseCase
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsScreen
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByTagIdUseCase: GetProductByTagIdUseCase,
) : BaseViewModel<ScanViewState, ScanViewAction>(
    initialViewState = ScanViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

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
}