package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.my_customers
import com.spoonofcode.feature.partner.presentation.send_notification
import com.spoonofcode.feature.partner.presentation.taggy_go_logo
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.my_customers),
    verticalScrollEnable = false,
)

@Composable
internal fun PartnerCustomerOverviewScreen(
    viewModel: PartnerCustomerOverviewViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: PartnerCustomerOverviewViewState,
    onAction: (PartnerCustomerOverviewViewAction) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements),
        modifier = Modifier.weight(1f)
    ) {
        items(viewState.customers) { customer ->
            val checkedCustomer =
                viewState.checkedUserIds.contains(customer.id)

            CustomerItem(
                item = customer,
                onClick = {
                    onAction(
                        PartnerCustomerOverviewViewAction.CheckedCustomer(customer.id!!)
                    )
                },
                checkedCustomer = checkedCustomer,
            )
        }
    }

    PrimaryButton(
        enabled = viewState.checkedUserIds.isNotEmpty(),
        text = stringResource(resource = Res.string.send_notification),
        onClick = { onAction(PartnerCustomerOverviewViewAction.NavigateToNotification) }
    )
}

@Composable
private fun CustomerItem(
    item: Customer,
    onClick: () -> Unit,
    checkedCustomer: Boolean,
) {
    Cards.ElevatedCard(
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Images.CircleImage(
                imageRes = Res.drawable.taggy_go_logo,
                imageSize = 64.dp,
            )
            Spacers.HorizontalBetweenFields()

            Column {
                Texts.BLB(item.name)
                Texts.BM(item.email)
            }

            Spacers.Weight1(this)

            Checkbox(
                checked = checkedCustomer,
                onCheckedChange = { onClick() }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerCustomerOverviewScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerCustomerOverviewViewState(
                customers = listOf(
                    Customer(
                        id = "1",
                        email = "william.henry.harrison@example-pet-store.com",
                        name = "Test",
                    ),
                ),
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
