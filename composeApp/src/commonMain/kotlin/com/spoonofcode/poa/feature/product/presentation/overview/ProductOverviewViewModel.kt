package com.spoonofcode.poa.feature.product.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.product.GetUserProductsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsScreen
import kotlinx.coroutines.launch

internal class ProductOverviewViewModel(
    private val getUserProductsUseCase: GetUserProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
) : BaseViewModel<ProductOverviewViewState, ProductOverviewViewAction>(ProductOverviewViewState()) {

    override fun onAction(action: com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction) {
        when (action) {
            is ProductOverviewViewAction.ChangeSearchText -> changeSearchText(action.searchText)
            ProductOverviewViewAction.InitView -> initView()
            is ProductOverviewViewAction.SelectProduct -> selectProduct(action.productId)
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getUserProductsUseCase()
                .onSuccess {
                    updateState {
                        copy(
                            screenState = ScreenState.CONTENT,
                            initProducts = it,
                            filteredProducts = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val products = currentState().initProducts
            val filteredProducts = getFilteredProductsByTextUseCase(
                searchText = searchText,
                products = products,
            )
            updateState {
                copy(searchText = searchText, filteredProducts = filteredProducts)
            }
        }
    }

    private fun selectProduct(productId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(ProductDetailsScreen(productId = productId))
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