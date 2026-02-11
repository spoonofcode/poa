package com.spoonofcode.feature.notification.presentation.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.data.ext.plus
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.data.ext.roundToNextHour
import com.spoonofcode.core.presentation.base.BaseViewState
import kotlinx.datetime.LocalDateTime

@Immutable
internal data class NotificationEditViewState(
    val itemIds: List<String> = emptyList(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
    val expirationDateTime: LocalDateTime = LocalDateTimeUtils.now()
//        .plus(ONE_WEEK_IN_HOURS)
//        .roundToNextHour(),
) : BaseViewState() {
    companion object {
        private const val ONE_WEEK_IN_HOURS = 168
    }
}