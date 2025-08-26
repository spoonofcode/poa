package com.spoonofcode.poa.feature.scan

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.product.GetProductByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : BaseViewModel<ScanViewState>(ScanViewState()) {

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
                val product = getProductByIdUseCase(productId = 1)
                viewModelNavigator.replaceAll(
                    listOf(
                        MainHostScreen(),
                        ProductDetailsScreen(productId = 1),
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