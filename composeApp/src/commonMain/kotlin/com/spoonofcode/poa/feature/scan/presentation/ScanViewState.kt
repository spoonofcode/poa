package com.spoonofcode.poa.feature.scan.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ScanViewState(
    val animationFilePath: String = "files/read_sensor_nfc.json",
    val contentDescription: String = "Scanning NFC tag",
) : BaseViewState()