package com.spoonofcode.poa.feature.partner.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.feature.partner.domain.usecase.GetPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.presentation.details.PartnerDetailsScreen
import kotlinx.coroutines.launch

internal class PartnerOverviewViewModel(
    private val getPartnersUseCase: GetPartnersUseCase,
    private val getPartnersFilteredByTextUseCase: GetPartnersFilteredByTextUseCase,
    private val getPartnerCategoriesUseCase: GetPartnerCategoriesUseCase,
) : BaseViewModel<PartnerOverviewViewState, PartnerOverviewViewAction>(PartnerOverviewViewState()) {

    override fun onAction(action: PartnerOverviewViewAction) {
        when (action) {
            is PartnerOverviewViewAction.ChangeSearchText -> changeSearchText(action.searchText)
            is PartnerOverviewViewAction.ClickCategory -> clickCategory(action.categoryId)
            PartnerOverviewViewAction.InitView -> initView()
            is PartnerOverviewViewAction.SelectPartner -> selectPartner(action.partnerId)
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnersUseCase()
                .onSuccess {
                    updateState {
                        copy(
                            initPartners = it,
                            filteredPartners = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }

            getPartnerCategoriesUseCase()
                .onSuccess {
                    showContentView {
                        copy(
                            partnerCategories = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun clickCategory(categoryId: String) {
        updateState {
            copy(
                selectedPartnerCategories = if (selectedPartnerCategories.contains(categoryId)) {
                    selectedPartnerCategories - categoryId
                } else {
                    selectedPartnerCategories + categoryId
                }
            )
        }
    }

    private fun changeSearchText(searchText: String) {
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

    private fun selectPartner(partnerId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerDetailsScreen(
                    partnerId = partnerId
                )
            )
        }
    }
}