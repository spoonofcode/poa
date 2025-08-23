package com.spoonofcode.poa.feature.partner.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class PartnerDetailsViewModel(
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
) : BaseViewModel<PartnerDetailsViewState>(PartnerDetailsViewState()) {

    fun initView(partnerId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val partner = getPartnerByIdUseCase(partnerId = partnerId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        partner = partner
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
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