package com.spoonofcode.poa.feature.notification.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons.IconTextButton
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification_type
import com.spoonofcode.poa.resources.to_selected_customers
import com.spoonofcode.poa.resources.to_selected_series
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationTypeScreen :
    BaseScreen<NotificationTypeViewModel, NotificationTypeViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notification_type

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationTypeViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationTypeViewModel?,
        viewState: NotificationTypeViewState,
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        navigateToPartnerCustomers = { viewModel?.navigateToPartnerCustomers() },
        navigateToPartnerProductSeries = { viewModel?.navigateToPartnerProductSeries() },
    )

    @Composable
    private fun ContentView(
        viewState: NotificationTypeViewState,
        navigateToPartnerCustomers: () -> Unit,
        navigateToPartnerProductSeries: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Person,
                    text = stringResource(resource = Res.string.to_selected_customers),
                    onClick = navigateToPartnerCustomers,
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.Default.WebStories,
                    text = stringResource(resource = Res.string.to_selected_series),
                    onClick = navigateToPartnerProductSeries,
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
    NotificationTypeScreen().PreviewContent(
        NotificationTypeViewState()
    )
}
// endregion