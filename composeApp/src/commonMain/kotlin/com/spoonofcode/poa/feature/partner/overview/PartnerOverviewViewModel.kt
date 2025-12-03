package com.spoonofcode.poa.feature.partner.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.partner.GetPartnerCategoriesUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersFilteredByTextUseCase
import com.spoonofcode.poa.core.domain.partner.GetPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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
            try {
                val partners = getPartnersUseCase()
                val partnerCategories = getPartnerCategoriesUseCase()
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        initPartners = partners,
                        filteredPartners = partners,
                        partnerCategories = partnerCategories,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
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
            viewModelNavigator.push(PartnerDetailsScreen(partnerId = partnerId))
        }
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