package com.spoonofcode.poa.feature.product.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetFilteredProductsByTextUseCase
import com.spoonofcode.poa.core.domain.user.GetUserProductsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductOverviewViewModel(
    private val getUserProductsUseCase: GetUserProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
) : BaseViewModel<ProductOverviewViewState>(ProductOverviewViewState()) {

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val products = getUserProductsUseCase()
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        initProducts = products,
                        filteredProducts = products,
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

    fun selectProduct(productId: Int) {
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