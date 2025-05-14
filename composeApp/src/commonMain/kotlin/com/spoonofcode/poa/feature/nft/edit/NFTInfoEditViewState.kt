package com.spoonofcode.poa.feature.nft.edit

import com.spoonofcode.poa.core.ext.plus
import com.spoonofcode.poa.core.ext.roundToNextHour
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

internal data class NFTInfoEditViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val title: String = "",
    val description: String = "",

    val clubs: Map<Int, String> = emptyMap(),
    val coaches: Map<Int, String> = emptyMap(),
    val rooms: Map<Int, String> = emptyMap(),
    val levels: Map<Int, String> = emptyMap(),
    val types: Map<Int, String> = emptyMap(),

    val eventTitle: String? = null,
    val eventDescription: String? = null,
    val selectedClubId: Int? = null,
    val selectedRoomId: Int? = null,
    val selectedLevelId: Int? = null,
    val selectedTypeId: Int? = null,
    val selectedMinNumberOfPeople: Int = DEFAULT_MIN_NUMBER_OF_PEOPLE,
    val selectedMaxNumberOfPeople: Int = DEFAULT_MAX_NUMBER_OF_PEOPLE,

    val cost: Int = DEFAULT_COST,
    val selectedCost: Int = DEFAULT_COST,

    val startDateTime: LocalDateTime = LocalDateTimeUtils.now().roundToNextHour(),
    val endDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(hours = 1).roundToNextHour(),
) : BaseViewState() {
    companion object {
        private const val DEFAULT_MIN_NUMBER_OF_PEOPLE = 8
        private const val DEFAULT_MAX_NUMBER_OF_PEOPLE = 12
        private const val DEFAULT_COST = 200
    }
}