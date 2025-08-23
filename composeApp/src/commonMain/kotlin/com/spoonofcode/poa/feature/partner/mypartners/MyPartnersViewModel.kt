package com.spoonofcode.poa.feature.partner.mypartners

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetMyPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.edit.PartnerEditScreen
import kotlinx.coroutines.launch

class MyPartnersViewModel(
    private val getMyPartnersUseCase: GetMyPartnersUseCase
) : BaseViewModel<MyPartnersViewState>(MyPartnersViewState()) {

    init {
        loadMyPartners()
    }

    fun loadMyPartners() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val partners = getMyPartnersUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        partners = partners
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }

    fun onPartnerClick(partnerId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerEditScreen(backNavigationEnable = true, partnerId = partnerId))
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