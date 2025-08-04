package com.spoonofcode.poa.feature.partner.details

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class PartnerDetailsViewModel(
) : BaseViewModel<PartnerDetailsViewState>(PartnerDetailsViewState()) {

    init {
        initView()
    }

    fun initView() {
        println("BARTEK init")
    }

    fun editPartner() {
        println("BARTEK edit")
//        viewModelScope.launch {
//            viewModelNavigator.push(ProductInfoEditScreen(screenMode = ScreenMode.Edit(nftId = currentState().nft!!.id)))
//        }
    }

    fun deletePartner() {
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
}