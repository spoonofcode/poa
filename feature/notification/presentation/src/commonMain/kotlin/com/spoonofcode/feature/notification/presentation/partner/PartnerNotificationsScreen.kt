package com.spoonofcode.feature.notification.presentation.partner

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notification_type

internal class PartnerNotificationsScreen :
    BaseScreen<PartnerNotificationsViewModel, PartnerNotificationsViewState, PartnerNotificationsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification_type

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerNotificationsViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationsViewState,
        onAction: (PartnerNotificationsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {

        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationsScreenContentPreview() {
    PartnerNotificationsScreen()
        .PreviewContent(
            PartnerNotificationsViewState()
        )
}
// endregion