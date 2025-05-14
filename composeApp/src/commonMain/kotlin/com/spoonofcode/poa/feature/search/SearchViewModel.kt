package com.spoonofcode.poa.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.GetFilteredNFTInfosByTextUseCase
import com.spoonofcode.poa.core.domain.GetFilteredNFTInfosUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.nft.details.NFTInfoDetailsScreen
import com.spoonofcode.poa.feature.search.filter.FilterScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class SearchViewModel(
    private val getFilteredNFTInfosUseCase: GetFilteredNFTInfosUseCase,
    private val getFilteredNFTInfosByTextUseCase: GetFilteredNFTInfosByTextUseCase,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val filteredNFTInfos = getFilteredNFTInfosUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initNFTInfos = filteredNFTInfos,
                        filteredNFTInfos = filteredNFTInfos,
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
            val nftInfos = currentState().initNFTInfos
            val filteredNFTInfos = getFilteredNFTInfosByTextUseCase(
                searchText = searchText,
                nftInfos = nftInfos,
            )
            updateState {
                copy(searchText = searchText, filteredNFTInfos = filteredNFTInfos)
            }
        }
    }

    fun selectNFTInfo(nftInfoId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(NFTInfoDetailsScreen(nftInfoId = nftInfoId))
        }
    }

    fun navigateToFilter() {
        viewModelScope.launch {
            viewModelNavigator.push(FilterScreen())
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
}