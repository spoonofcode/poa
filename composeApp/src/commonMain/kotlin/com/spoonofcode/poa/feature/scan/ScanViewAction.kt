package com.spoonofcode.poa.feature.scan

sealed interface ScanViewAction {
    data class CheckProduct(val tagId: String) : ScanViewAction
    data object InitView : ScanViewAction
}