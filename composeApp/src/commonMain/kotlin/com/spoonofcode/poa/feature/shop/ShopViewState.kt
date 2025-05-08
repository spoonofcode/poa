package com.spoonofcode.poa.feature.shop

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ShopViewState(
    override val isLoadingView: Boolean = false,
    val url: String = "https://shop.proof-of-wear.com/"
) : BaseViewState()