package com.spoonofcode.poa.feature.scan

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.product.GetProductByTagIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import com.spoonofcode.poa.feature.product.overview.ProductOverviewScreen
import com.spoonofcode.poa.feature.product.overview.ProductOverviewTab
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByTagIdUseCase: GetProductByTagIdUseCase,
) : BaseViewModel<ScanViewState>(ScanViewState()) {

    init {
        checkProduct("04A971E2E51090")
    }

    fun initView() {
        updateState {
            copy(
                screenState = ScreenState.CONTENT,
            )
        }
    }

    fun checkProduct(tagId: String) {
        viewModelScope.launch {
            showLoadingView()
            try {
//                val product = getProductByTagIdUseCase(tagId = tagId)
                viewModelNavigator.replaceAll(
                    listOf(
                        MainHostScreen(selectedTab = ProductOverviewTab)
//                        ProductDetailsScreen(
//                            productId = product.id,
//                            hasOwnerUser = product.ownerUser != null,
//                        )
                    )
                )
            } catch (e: Exception) {
                showErrorView()
            }
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