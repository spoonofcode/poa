package com.spoonofcode.feature.scan.nfc.presentation

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.Platform
import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.scan.nfc.domain.usecase.GetNFCTagUseCase
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getNFCTagUseCase: GetNFCTagUseCase,
    private val platform: Platform,
) : BaseViewModel<ScanViewState, ScanViewAction, Nothing>(
    initialViewState = ViewState.Content(ScanViewState()),
) {

    override fun onAction(action: ScanViewAction) {
        when (action) {
            is ScanViewAction.InitView -> initView()
            is ScanViewAction.CheckProduct -> checkProduct(action.nfcTag)
            ScanViewAction.ScanAgain -> scanAgain()
        }
    }

    private fun initView() {
        updateContentState {
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
                getNFCTagUseCase(url = transformUrl(urlOnTag))
                    .onSuccess { nfcTag ->
                        if (nfcTag.tagType in VALID_NFC_TAG_TYPES) {
                            getProductByIdUseCase(productId = nfcTag.publicId)
                                .onSuccess { product ->
                                    viewModelNavigator.push(
                                        routeResolver.resolve(
                                            ProductModuleRoute.ProductDetails(
                                                productId = product.id,
                                            )
                                        )
                                    )
                                }
                                .onFailure {
                                    showErrorView()
                                }
                        } else {
                            updateContentState {
                                copy(
                                    isProductNotFoundViewVisible = true,
                                )
                            }
                        }
                    }.onFailure {
                        showErrorView()
                    }


            } else {
                updateContentState {
                    copy(
                        isProductNotFoundViewVisible = true,
                    )
                }
            }
        }
    }

    private fun scanAgain() {
        updateContentState {
            copy(
                isProductNotFoundViewVisible = false,
            )
        }
    }

    companion object {
        private const val POA_DOMAIN_NAME = "proof-of-wear.com"
        private const val POA_AUTH_DOMAIN_NAME = "auth.proof-of-wear.com"
        private const val POA_TEST_DOMAIN_NAME = "pow-test1-960238188791.europe-west1.run.app"
        private val VALID_NFC_TAG_TYPES = setOf("nft", "unique_item")

        fun transformUrl(originalUrl: String): String {
            return if(originalUrl.contains(POA_AUTH_DOMAIN_NAME)){
                originalUrl.replace(POA_AUTH_DOMAIN_NAME, POA_TEST_DOMAIN_NAME)
            } else {
                originalUrl.replace(POA_DOMAIN_NAME, POA_TEST_DOMAIN_NAME)
            }
        }
    }
}
