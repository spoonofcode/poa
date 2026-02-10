package com.spoonofcode.feature.scan.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.product.domain.usecase.GetProductByTagIdUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByTagIdUseCase: GetProductByTagIdUseCase,
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
            getProductByTagIdUseCase(tagId = tagId)
                .onSuccess {
                    viewModelNavigator.push(
                        routeResolver.resolve(
                            ProductModule.ProductDetailsScreen(
                                productId = it.id,
                                hasOwnerUser = it.ownerUserId != null,
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