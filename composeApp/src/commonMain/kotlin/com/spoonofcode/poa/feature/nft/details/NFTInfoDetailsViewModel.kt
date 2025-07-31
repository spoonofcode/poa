package com.spoonofcode.poa.feature.nft.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import com.spoonofcode.poa.core.domain.old.GetNFTInfoByIdUseCase

internal class NFTInfoDetailsViewModel(
    private val getNFTInfoByIdUseCase: GetNFTInfoByIdUseCase,
//    private val deleteNFTUseCase: DeleteNFTUseCase,
) : BaseViewModel<NFTInfoDetailsViewState>(NFTInfoDetailsViewState()) {

    fun initView(nftInfoId: String) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val nftInfo = getNFTInfoByIdUseCase(nftInfoId = nftInfoId)
                updateState {
                    copy(
                        isLoadingView = false,
                        nftInfo = nftInfo
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun editNFT() {
//        viewModelScope.launch {
//            viewModelNavigator.push(NFTInfoEditScreen(screenMode = ScreenMode.Edit(nftId = currentState().nft!!.id)))
//        }
    }

    fun deleteNFT() {
//        viewModelScope.launch {
//            showLoadingView()
//            try {
//                val currentState = currentState()
//                deleteNFTUseCase(
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

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}