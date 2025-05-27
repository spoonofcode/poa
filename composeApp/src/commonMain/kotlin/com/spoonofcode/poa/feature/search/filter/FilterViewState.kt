package com.spoonofcode.poa.feature.search.filter

import com.spoonofcode.poa.core.ext.plus
import com.spoonofcode.poa.core.ext.roundToNextHour
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

internal data class FilterViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val nftSeries: Map<String, String> = emptyMap(),
    val blockchains: Map<String, String> = emptyMap(),
    val selectedSerieId: String = ALL_OPTION_ID,
    val selectedBlockchainId: String = ALL_OPTION_ID,

    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = ONE_WEEK_IN_HOURS)
        .roundToNextHour(),
) : BaseViewState() {
    companion object {
        const val ALL_OPTION_ID = "0"
        const val ALL_OPTION_NAME = "All"
        const val ONE_WEEK_IN_HOURS = 168
    }
}
