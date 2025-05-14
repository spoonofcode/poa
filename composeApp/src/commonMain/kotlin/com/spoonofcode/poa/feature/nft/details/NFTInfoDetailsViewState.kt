package com.spoonofcode.poa.feature.nft.details

import com.spoonofcode.poa.core.model.NFTInfo
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class NFTInfoDetailsViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val nftInfo: NFTInfo? = null,
) : BaseViewState(
    isLoadingView = isLoadingView,
)