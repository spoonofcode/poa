package com.spoonofcode.feature.scan.nfc.presentation

import com.spoonofcode.core.nfc.NfcTag
import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ScanViewAction : BaseViewAction {
    data class CheckProduct(
        val nfcTag: NfcTag,
    ) : ScanViewAction

    data object ScanAgain : ScanViewAction
}
