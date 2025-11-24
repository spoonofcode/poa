package com.spoonofcode.poa.feature.scan

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface ScanViewAction: BaseViewAction {
    data class CheckProduct(val tagId: String) : ScanViewAction
    data object InitView : ScanViewAction
}