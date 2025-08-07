package com.spoonofcode.poa.feature.partner.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class PartnerEditViewModel(
    private val getPartnerByIdUseCase: GetPartnerByIdUseCase,
) : BaseViewModel<PartnerEditViewState>(PartnerEditViewState()) {

    fun initView(partnerId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val partner = getPartnerByIdUseCase(partnerId = partnerId)
                updateState {
                    copy(
                        isLoadingView = false,
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