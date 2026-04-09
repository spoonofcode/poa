package com.spoonofcode.feature.scan.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val platform: Platform,
) : BaseViewModel<ScanViewState, ScanViewAction>(
    initialViewState = ScanViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    init {
        updateState {
            ScanViewState(platformType = platform.platformType)
        }
    }

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.CheckProduct -> checkProduct(action.tagId)
        }
    }

    private fun checkProduct(tagId: String) {
        viewModelScope.launch {
            showLoadingView()
            getProductByIdUseCase(productId = tagId)
                .onSuccess {
                    viewModelNavigator.push(
                        routeResolver.resolve(
                            ProductModule.ProductDetailsScreen(
                                productId = it.id,
                                hasOwnerUser = it.ownerEmail != null,
                            )
                        )
                    )
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}