package com.spoonofcode.feature.product.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.feature.product.domain.usecase.GetUserProductsUseCase
import kotlinx.coroutines.launch

internal class ProductOverviewViewModel(
    private val getUserProductsUseCase: GetUserProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
) : BaseViewModel<ProductOverviewViewState, ProductOverviewViewAction, Nothing>(
    initialViewState = ViewState.Content(ProductOverviewViewState()),
) {
    override val startAction = {
        initView()
    }

    override fun onAction(action: ProductOverviewViewAction) {
        when (action) {
            is ProductOverviewViewAction.ChangeSearchText -> changeSearchText(action.searchText)
            is ProductOverviewViewAction.SelectProduct -> selectProduct(action.productId)
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getUserProductsUseCase()
                .onSuccess {
                    updateContentState {
                        copy(
                            initProducts = it,
                            filteredProducts = it,
                        )
                    }
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeSearchText(searchText: String) {
        viewModelScope.launch {
            val products = currentContentState().initProducts
            val filteredProducts = getFilteredProductsByTextUseCase(
                searchText = searchText,
                products = products,
            )
            updateContentState {
                copy(searchText = searchText, filteredProducts = filteredProducts)
            }
        }
    }

    private fun selectProduct(productId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                ProductModuleRoute.ProductDetails(productId = productId)
            )
        }
    }
}