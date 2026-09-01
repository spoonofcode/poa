package com.spoonofcode.feature.product.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.GetFilteredProductsByTextUseCase
import com.spoonofcode.feature.product.domain.usecase.GetUserProductsUseCase
import com.spoonofcode.feature.product.presentation.ProductEvent
import com.spoonofcode.feature.product.presentation.ProductEventBus
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.product_added
import com.spoonofcode.feature.product.presentation.product_deleted
import com.spoonofcode.feature.product.presentation.product_updated
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

internal class ProductOverviewViewModel(
    private val getUserProductsUseCase: GetUserProductsUseCase,
    private val getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase,
    private val productEventBus: ProductEventBus,
) : BaseViewModel<ProductOverviewViewState, ProductOverviewViewAction, Nothing>() {
    init {
        observeProductEvents()
    }

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
                    setContentState(
                        ProductOverviewViewState(
                            initProducts = it,
                            filteredProducts = it,
                        )
                    )
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

    private fun observeProductEvents() {
        viewModelScope.launch {
            productEventBus.events.collect { event ->
                initView()
                when (event) {
                    ProductEvent.ProductAdded -> {
                        showSuccessSnackbar(getString(Res.string.product_added))
                    }

                    ProductEvent.ProductDeleted -> {
                        showSuccessSnackbar(getString(Res.string.product_deleted))
                    }

                    ProductEvent.ProductUpdated -> {
                        showSuccessSnackbar(getString(Res.string.product_updated))
                    }
                }
            }
        }
    }
}
