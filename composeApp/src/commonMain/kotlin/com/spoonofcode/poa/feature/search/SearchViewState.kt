package com.spoonofcode.poa.feature.search

import com.spoonofcode.poa.core.model.old.NFTInfo
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class SearchViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val searchText: String = "",
    val initNFTInfos: List<NFTInfo> = emptyList(),
    val filteredNFTInfos: List<NFTInfo> = emptyList(),
) : BaseViewState()