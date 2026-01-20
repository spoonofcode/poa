package com.spoonofcode.poa.feature.scan.presentation

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface ScanViewAction: BaseViewAction {
    data class CheckProduct(val tagId: String) : ScanViewAction
}