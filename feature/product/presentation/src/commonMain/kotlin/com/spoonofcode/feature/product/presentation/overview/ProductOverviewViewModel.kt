package com.spoonofcode.feature.product.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.feature.product.domain.usecase.GetUserProductsUseCase
import kotlinx.coroutines.launch

internal class ProductOverviewViewModel(
    private val getUserProductsUseCase: GetUserProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
) : BaseViewModel<ProductOverviewViewState, ProductOverviewViewAction>(ProductOverviewViewState()) {

    override fun onAction(action: ProductOverviewViewAction) {
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
                    showContentView {
                        copy(
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
            val products = currentContentViewState().initProducts
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
            viewModelNavigator.push(
                routeResolver.resolve(
                    ProductModuleRoute.ProductDetails(productId = productId)
                )
            )
        }
    }
}