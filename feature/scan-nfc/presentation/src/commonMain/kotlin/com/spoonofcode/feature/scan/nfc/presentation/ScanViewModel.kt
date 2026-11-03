package com.spoonofcode.feature.scan.nfc.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.scan.nfc.domain.usecase.GetNFCTagUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getNFCTagUseCase: GetNFCTagUseCase,
    private val platform: Platform,
) : BaseViewModel<ScanViewState, ScanViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.CheckProduct -> checkProduct(action.nfcTag)
            ScanViewAction.ScanAgain -> scanAgain()
        }
    }

    private fun initView() {
        setContentState(
            ScanViewState(
                platformType = platform.platformType,
            ),
        )
        showContentView()
    }

    private fun checkProduct(nfcTag: NfcTag) {
        viewModelScope.launch {
            showLoadingView()
            getNFCTagUseCase(url = nfcTag.ndef)
                .onSuccess { tag ->
                    getProductByIdUseCase(productId = tag.publicId)
                        .onSuccess { product ->
                            viewModelNavigator.push(
                                ProductModuleRoute.ProductDetails(
                                    productId = product.id,
                                ),
                            )
                            showContentView()
                        }.onFailure {
                            showErrorView()
                        }
                }.onFailure { error ->
                    if (error is IllegalArgumentException) {
                        showProductNotFound()
                    } else {
                        showErrorView()
                    }
                }
        }
    }

    private fun showProductNotFound() {
        showContentView()
        updateContentState { copy(isProductNotFoundViewVisible = true) }
    }

    private fun scanAgain() {
        updateContentState { copy(isProductNotFoundViewVisible = false) }
    }
}
