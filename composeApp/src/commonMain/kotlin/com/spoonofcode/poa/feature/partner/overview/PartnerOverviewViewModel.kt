package com.spoonofcode.poa.feature.partner.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByCategoryUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class PartnerOverviewViewModel(
    private val getPartnersFilteredByCategoryUseCase: GetPartnersFilteredByCategoryUseCase,
    private val getPartnersFilteredByTextUseCase: GetPartnersFilteredByTextUseCase,
) : BaseViewModel<PartnerOverviewViewState>(PartnerOverviewViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val filteredPartners = getPartnersFilteredByCategoryUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initPartners = filteredPartners,
                        filteredPartners = filteredPartners,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val products = currentState().initPartners
            val filteredPartners = getPartnersFilteredByTextUseCase(
                searchText = searchText,
                products = products,
            )
            updateState {
                copy(searchText = searchText, filteredPartners = filteredPartners)
            }
        }
    }

    fun selectPartner(partnerId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(PartnerDetailsScreen(partnerId = partnerId))
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