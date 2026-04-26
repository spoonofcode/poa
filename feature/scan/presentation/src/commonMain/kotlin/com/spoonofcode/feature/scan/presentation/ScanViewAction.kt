package com.spoonofcode.feature.scan.presentation

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ScanViewAction: BaseViewAction {
    data object InitView : ScanViewAction
    data class CheckProduct(val tagId: String) : ScanViewAction
}