package com.spoonofcode.feature.partner.presentation.customer.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.my_customers
import com.spoonofcode.core.presentation.poa
import com.spoonofcode.core.presentation.send_notification
import org.jetbrains.compose.resources.stringResource

internal class CustomerOverviewScreen(
    val partnerId: String,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<PartnerCustomerOverviewViewModel, PartnerCustomerOverviewViewState, PartnerCustomerOverviewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.my_customers

    override fun provideInitAction(onAction: (PartnerCustomerOverviewViewAction) -> Unit): () -> Unit =
        { onAction(PartnerCustomerOverviewViewAction.InitView(partnerId = partnerId)) }

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
                items(viewState.partnerUsers) { user ->
                    val checkedCustomer =
                        viewState.checkedUserIds.contains(user.id)

                    CustomerItem(
                        item = user,
                        onClick = { onAction(PartnerCustomerOverviewViewAction.CheckedCustomer(user.id)) },
                        checkedCustomer = checkedCustomer,
                    )
                }
            }

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                enabled = viewState.checkedUserIds.isNotEmpty(),
                text = stringResource(resource = Res.string.send_notification),
                onClick = { onAction(PartnerCustomerOverviewViewAction.NavigateToNotification) }
            )
        }
    }

    @Composable
    fun CustomerItem(
        item: PartnerUser,
        onClick: () -> Unit,
        checkedCustomer: Boolean,
    ) {
        Cards.ElevatedCard(
            onClick = onClick
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Images.CircleImage(imageRes = Res.drawable.poa, imageSize = 64.dp)

                Spacers.HorizontalBetweenFields()

                Column {
                    Texts.BLB(item.fullName)
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
    CustomerOverviewScreen(
        partnerId = "1"
    ).PreviewContent(
        viewState = PartnerCustomerOverviewViewState(),
    )
}
// endregion