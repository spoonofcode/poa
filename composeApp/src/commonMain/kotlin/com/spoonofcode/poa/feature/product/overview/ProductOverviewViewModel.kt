package com.spoonofcode.poa.feature.product.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductOverviewViewModel(
    private val getFilteredProductsUseCase: GetFilteredProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
) : BaseViewModel<ProductOverviewViewState>(ProductOverviewViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val filteredProducts = getFilteredProductsUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initProducts = filteredProducts,
                        filteredProducts = filteredProducts,
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

    fun selectProduct(productId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(ProductDetailsScreen(productId = productId))
        }
    }

    fun navigateToFilter() {
//        viewModelScope.launch {
//            viewModelNavigator.push(FilterScreen())
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
}