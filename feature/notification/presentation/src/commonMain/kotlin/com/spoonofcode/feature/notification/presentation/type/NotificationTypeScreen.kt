package com.spoonofcode.feature.notification.presentation.type

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
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons.IconTextButton
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.notification_type
import com.spoonofcode.core.presentation.to_all
import com.spoonofcode.core.presentation.to_all_my_customers
import com.spoonofcode.core.presentation.to_selected_customers
import com.spoonofcode.core.presentation.to_selected_series
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

internal class NotificationTypeScreen :
    BaseScreen<com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewModel, com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewState, com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification_type

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewState,
        onAction: (com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Groups,
                    text = stringResource(resource = Res.string.to_all),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllCustomers) },
                    modifier = Modifier.weight(1f),
                    enabled = viewState.buttonToAllEnable,
                )

                IconTextButton(
                    icon = Icons.Default.Diversity3,
                    text = stringResource(resource = Res.string.to_all_my_customers),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllPartnerCustomers) },
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
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerCustomers) },
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.Default.WebStories,
                    text = stringResource(resource = Res.string.to_selected_series),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerProductSeries) },
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
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeScreen()
        .PreviewContent(
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewState()
    )
}
// endregion