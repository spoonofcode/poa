package com.spoonofcode.feature.scan.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.scan.domain.usecase.GetTagPublicIdUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getTagPublicIdUseCase: GetTagPublicIdUseCase,
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
            val urlOnTag = nfcTag.ndef
            if (urlOnTag?.contains(POA_DOMAIN_NAME) == true) {
                getTagPublicIdUseCase(url = urlOnTag)
                    .onSuccess { nfcTag ->
                        if (nfcTag.tagType in VALID_NFC_TAG_TYPES) {
                            getProductByIdUseCase(productId = nfcTag.publicId)
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
                    }.onFailure {
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
        private val VALID_NFC_TAG_TYPES = setOf("nft", "uniqe_tag")
    }
}