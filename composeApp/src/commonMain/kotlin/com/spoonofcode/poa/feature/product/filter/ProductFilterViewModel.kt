package com.spoonofcode.poa.feature.product.filter

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetProductFilterDataUseCase
import com.spoonofcode.poa.core.domain.product.SetProductFilterDataUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.product.filter.ProductFilterViewState.Companion.ALL_OPTION_ID
import com.spoonofcode.poa.feature.product.filter.ProductFilterViewState.Companion.ALL_OPTION_NAME
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductFilterViewModel(
//    private val loadProductFilterFormDataUseCase: LoadProductFilterFormDataUseCase,
    private val setProductFilterDataUseCase: SetProductFilterDataUseCase,
    private val getProductFilterDataUseCase: GetProductFilterDataUseCase,
) : BaseViewModel<ProductFilterViewState>(ProductFilterViewState()) {
    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
//                val productFilterFormData = loadProductFilterFormDataUseCase()
                val productFilterData = getProductFilterDataUseCase()
                val selectedName = productFilterData.selectedName ?: ALL_OPTION_ID
                val selectedCollectionName =
                    productFilterData.selectedCollectionName ?: ALL_OPTION_ID
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun changeCollectionName(selectedCollectionName: String) {
        viewModelScope.launch {
            updateState {
                copy(selectedCollectionName = selectedCollectionName)
            }
        }
    }

    fun applyFilter() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                setProductFilterDataUseCase.invoke(
                    selectedName = currentState.selectedName,
                    selectedCollectionName = currentState.selectedCollectionName,
                )
                navigateBack()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun addAllOption(): Map<Int, String> = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME)

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

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.CONTENT,
            )
        }
    }
}