package com.spoonofcode.feature.partner.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerCategoriesUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByPartnerCategoryUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersFilteredByTextUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnersUseCase
import com.spoonofcode.feature.partner.presentation.details.PartnerDetailsScreen
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class PartnerOverviewViewModel(
    private val getPartnersUseCase: GetPartnersUseCase,
    private val getPartnersFilteredByPartnerCategoryUseCase: GetPartnersFilteredByPartnerCategoryUseCase,
    private val getPartnersFilteredByTextUseCase: GetPartnersFilteredByTextUseCase,
    private val getPartnerCategoriesUseCase: GetPartnerCategoriesUseCase,
) : BaseViewModel<PartnerOverviewViewState, PartnerOverviewViewAction>(PartnerOverviewViewState()) {

    override fun onAction(action: PartnerOverviewViewAction) {
        when (action) {
            is PartnerOverviewViewAction.ChangeSearchText -> changeSearchText(action.searchText)
            is PartnerOverviewViewAction.ClickCategory -> clickCategory(action.categoryName)
            PartnerOverviewViewAction.InitView -> initView()
            is PartnerOverviewViewAction.SelectPartner -> selectPartner(action.partnerId)
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            val partnersDeferred = async { getPartnersUseCase() }
            val categoriesDeferred = async { getPartnerCategoriesUseCase() }

            runCatching {
                val partners = partnersDeferred.await().getOrThrow()
                val categories = categoriesDeferred.await().getOrThrow()
                partners to categories
            }.onSuccess { (partners, categories) ->
                showContentView {
                    copy(
                        initPartners = partners,
                        filteredPartners = partners,
                        partnerCategories = categories,
                    )
                }
            }.onFailure {
                showErrorView()
            }
        }
    }

    private fun clickCategory(categoryName: String) {
        updateState {
            val newSelectedCategories = if (selectedPartnerCategories.contains(categoryName)) {
                selectedPartnerCategories - categoryName
            } else {
                selectedPartnerCategories + categoryName
            }
            copy(selectedPartnerCategories = newSelectedCategories)
        }
        applyFilters()
    }

    private fun changeSearchText(searchText: String) {
        updateState { copy(searchText = searchText) }
        applyFilters()
    }

    private fun applyFilters() {
        val currentState = currentState()

        var filtered = currentState.initPartners

        filtered = getPartnersFilteredByTextUseCase(
            searchText = currentState.searchText,
            partners = filtered
        )

        filtered = getPartnersFilteredByPartnerCategoryUseCase(
            partnerCategories = currentState.selectedPartnerCategories,
            partners = filtered
        )

        updateState {
            copy(filteredPartners = filtered)
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
