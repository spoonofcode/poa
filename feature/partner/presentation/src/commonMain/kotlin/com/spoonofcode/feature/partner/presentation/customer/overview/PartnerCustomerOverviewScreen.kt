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
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.my_customers
import com.spoonofcode.feature.partner.presentation.send_notification
import com.spoonofcode.feature.partner.presentation.taggy_go_logo
import org.jetbrains.compose.resources.stringResource

internal class CustomerOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<PartnerCustomerOverviewViewModel, PartnerCustomerOverviewViewState, PartnerCustomerOverviewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.my_customers

    override fun provideInitAction(onAction: (PartnerCustomerOverviewViewAction) -> Unit): () -> Unit =
        { onAction(PartnerCustomerOverviewViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerCustomerOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerCustomerOverviewViewState,
        onAction: (PartnerCustomerOverviewViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.customers) { customer ->
                    val checkedCustomer =
                        viewState.checkedUserEmails.contains(customer.email)

                    CustomerItem(
                        item = customer,
                        onClick = {
                            onAction(
                                PartnerCustomerOverviewViewAction.CheckedCustomer(customer.email)
                            )
                        },
                        checkedCustomer = checkedCustomer,
                    )
                }
            }

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                enabled = viewState.checkedUserEmails.isNotEmpty(),
                text = stringResource(resource = Res.string.send_notification),
                onClick = { onAction(PartnerCustomerOverviewViewAction.NavigateToNotification) }
            )
        }
    }

    @Composable
    fun CustomerItem(
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
}

// region previews
@Preview
@Composable
fun CustomerOverviewScreenContentPreview() {
    CustomerOverviewScreen().PreviewContent(
        viewState = PartnerCustomerOverviewViewState(),
    )
}
// endregion