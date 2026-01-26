package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons.IconTextButton
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification_type
import com.spoonofcode.poa.resources.to_all
import com.spoonofcode.poa.resources.to_all_my_customers
import com.spoonofcode.poa.resources.to_selected_customers
import com.spoonofcode.poa.resources.to_selected_series
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

internal class NotificationTypeScreen :
    BaseScreen<NotificationTypeViewModel, NotificationTypeViewState, NotificationTypeViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification_type

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationTypeViewModel>()

    @Composable
    override fun provideContent(
        viewState: NotificationTypeViewState,
        onAction: (NotificationTypeViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Groups,
                    text = stringResource(resource = Res.string.to_all),
                    onClick = { onAction(NotificationTypeViewAction.NavigateToAllCustomers) },
                    modifier = Modifier.weight(1f),
                    enabled = viewState.buttonToAllEnable,
                )

                IconTextButton(
                    icon = Icons.Default.Diversity3,
                    text = stringResource(resource = Res.string.to_all_my_customers),
                    onClick = { onAction(NotificationTypeViewAction.NavigateToAllPartnerCustomers) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.PersonSearch,
                    text = stringResource(resource = Res.string.to_selected_customers),
                    onClick = { onAction(NotificationTypeViewAction.NavigateToPartnerCustomers) },
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.Default.WebStories,
                    text = stringResource(resource = Res.string.to_selected_series),
                    onClick = { onAction(NotificationTypeViewAction.NavigateToPartnerProductSeries) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationTypeScreenContentPreview() {
    NotificationTypeScreen()
        .PreviewContent(
            NotificationTypeViewState()
    )
}
// endregion