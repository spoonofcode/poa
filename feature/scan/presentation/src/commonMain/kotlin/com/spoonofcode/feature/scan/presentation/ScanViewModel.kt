package com.spoonofcode.feature.scan.presentation

import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState

// TODO #109 Fix after migration to feature modules
internal class ScanViewModel(
//    private val getProductByTagIdUseCase: GetProductByTagIdUseCase,
//    private val platform: Platform,
) : BaseViewModel<ScanViewState, ScanViewAction>(
    initialViewState = ScanViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

//    init {
//        updateState {
//            ScanViewState(platformType = platform.platformType)
//        }
//    }

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.CheckProduct -> checkProduct(action.tagId)
        }
    }

    private fun checkProduct(tagId: String) {
        // TODO #109 Fix after migration to feature modules
//        viewModelScope.launch {
//            showLoadingView()
//            getProductByTagIdUseCase(tagId = tagId)
//                .onSuccess {
//                    viewModelNavigator.push(
//                        ProductDetailsScreen(
//                            productId = it.id,
//                            hasOwnerUser = it.ownerUserId != null,
//                        )
//                    )
//                }
//                .onFailure {
//                    showErrorView()
//                }
//        }
    }
}