package com.spoonofcode.feature.partner.presentation.notification.type

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
import com.spoonofcode.core.presentation.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.notification_type
import com.spoonofcode.feature.partner.presentation.to_all
import com.spoonofcode.feature.partner.presentation.to_all_my_customers
import com.spoonofcode.feature.partner.presentation.to_selected_customers
import com.spoonofcode.feature.partner.presentation.to_selected_series

internal class PartnerNotificationTypeScreen :
    BaseScreen<PartnerNotificationTypeViewModel, PartnerNotificationTypeViewState, PartnerNotificationTypeViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification_type

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerNotificationTypeViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationTypeViewState,
        onAction: (PartnerNotificationTypeViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Groups,
                    text = stringResource(resource = Res.string.to_all),
                    onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToAllCustomers) },
                    modifier = Modifier.weight(1f),
                    enabled = viewState.buttonToAllEnable,
                )

                IconTextButton(
                    icon = Icons.Default.Diversity3,
                    text = stringResource(resource = Res.string.to_all_my_customers),
                    onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToAllPartnerCustomers) },
                    modifier = Modifier.weight(1f),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.PersonSearch,
                    text = stringResource(resource = Res.string.to_selected_customers),
                    onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerCustomers) },
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.Default.WebStories,
                    text = stringResource(resource = Res.string.to_selected_series),
                    onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerProductSeries) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationTypeScreenContentPreview() {
    PartnerNotificationTypeScreen()
        .PreviewContent(
            PartnerNotificationTypeViewState()
        )
}
// endregion
