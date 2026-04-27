package com.spoonofcode.feature.scan.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val platform: Platform,
) : BaseViewModel<ScanViewState, ScanViewAction>(
    initialViewState = ScanViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.InitView -> initView()
            is ScanViewAction.CheckProduct -> checkProduct(action.nfcTag)
            ScanViewAction.ScanAgain -> scanAgain()
        }
    }

    private fun initView() {
        showContentView {
            copy(
                platformType = platform.platformType
            )
        }
    }

    private fun checkProduct(nfcTag: NfcTag) {
        viewModelScope.launch {
            showLoadingView()
            if (nfcTag.ndef?.contains(POA_DOMAIN_NAME) == true) {
                getProductByIdUseCase(productId = "tagId")
                    .onSuccess {
                        viewModelNavigator.push(
                            routeResolver.resolve(
                                ProductModuleRoute.ProductDetails(
                                    productId = it.id,
                                    hasOwnerUser = it.ownerEmail != null,
                                )
                            )
                        )
                    }
                    .onFailure {
                        showErrorView()
                    }
            } else {
                showContentView {
                    copy(
                        isProductNotFoundViewVisible = true,
                    )
                }
            }
        }
    }

    private fun scanAgain() {
        updateState {
            copy(
                isProductNotFoundViewVisible = false,
            )
        }
    }

    companion object {
        private const val POA_DOMAIN_NAME = "proof-of-wear.com"
    }
}