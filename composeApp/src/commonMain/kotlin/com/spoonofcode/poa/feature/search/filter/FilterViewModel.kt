package com.spoonofcode.poa.feature.search.filter

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.GetFilterDataUseCase
import com.spoonofcode.poa.core.domain.old.LoadSportEventFilterFormDataUseCase
import com.spoonofcode.poa.core.domain.SetFilterDataUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.search.filter.FilterViewState.Companion.ALL_OPTION_ID
import com.spoonofcode.poa.feature.search.filter.FilterViewState.Companion.ALL_OPTION_NAME
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class FilterViewModel(
    private val loadSportEventFilterFormDataUseCase: LoadSportEventFilterFormDataUseCase,
    private val setFilterDataUseCase: SetFilterDataUseCase,
    private val getFilterDataUseCase: GetFilterDataUseCase,
) : BaseViewModel<FilterViewState>(FilterViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val sportEventFilterFormData = loadSportEventFilterFormDataUseCase()
                val filterData = getFilterDataUseCase()
                val selectedSerieId = filterData.selectedSerieId ?: ALL_OPTION_ID
                val selectedBlockchainId = filterData.selectedBlockchainId ?: ALL_OPTION_ID
                updateState {
                    copy(
                        isLoadingView = false,
                        selectedSerieId = selectedSerieId,
                        selectedBlockchainId = selectedBlockchainId,
                        nftSeries = addAllOption().plus(sportEventFilterFormData.nftSeries.associate { it.id to it.fullName }),
                        blockchains = addAllOption().plus(sportEventFilterFormData.blockchains.associate { it.id to it.fullName }),
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun changeNFTSerie(selectedSerieId: String) {
        viewModelScope.launch {
            updateState {
                copy(selectedSerieId = selectedSerieId)
            }
        }
    }

    fun changeBlockchain(selectedBlockchainId: String) {
        viewModelScope.launch {
            updateState {
                copy(selectedBlockchainId = selectedBlockchainId)
            }
        }
    }

    fun applyFilter() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                setFilterDataUseCase.invoke(
                    selectedSerieId = currentState.selectedSerieId,
                    selectedBlockchainId = currentState.selectedBlockchainId,
                )
                navigateBack()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun addAllOption(): Map<String, String> = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME)

    private fun navigateBack() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
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

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}