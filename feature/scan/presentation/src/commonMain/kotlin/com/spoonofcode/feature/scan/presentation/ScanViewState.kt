package com.spoonofcode.feature.scan.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.PlatformType
//import com.spoonofcode.poa.app.PlatformType
import com.spoonofcode.core.presentation.base.BaseViewState

@Immutable
internal data class ScanViewState(
    val animationFilePath: String = "files/read_sensor_nfc.json",
    val contentDescription: String = "Scanning NFC tag",
    val platformType: PlatformType = PlatformType.Android,
    val isProductNotFoundViewVisible: Boolean = false,
) : BaseViewState()