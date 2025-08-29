package com.spoonofcode.poa.feature.notification.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ext.plus
import com.spoonofcode.poa.core.ext.roundToNextHour
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime

@Immutable
internal data class NotificationEditViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val seriesIds: List<String> = emptyList(),
    val title: String = "",
    val text: String = "",
    val link: String = "",
    val expirationDateTime: LocalDateTime = LocalDateTimeUtils.now().plus(ONE_WEEK_IN_HOURS)
        .roundToNextHour(),
) : BaseViewState(screenState) {
    companion object {
        private const val ONE_WEEK_IN_HOURS = 168
    }
}