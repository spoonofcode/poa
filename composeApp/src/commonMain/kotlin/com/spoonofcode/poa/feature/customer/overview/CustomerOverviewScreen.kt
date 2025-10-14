package com.spoonofcode.poa.feature.customer.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.CircleImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_customers
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.send_notification
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class CustomerOverviewScreen(
    val partnerId: Int,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<CustomerOverviewViewModel, CustomerOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.my_customers

    override fun provideInitAction(viewModel: CustomerOverviewViewModel?): () -> Unit =
        { viewModel?.initView(partnerId = partnerId) }

    @Composable
    override fun provideViewModel() = koinViewModel<CustomerOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: CustomerOverviewViewModel?,
        viewState: CustomerOverviewViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        checkedCustomer = { viewModel?.checkedCustomer(it) },
        navigateToNotification = { viewModel?.navigateToNotification() },
    )

    @Composable
    internal fun ContentView(
        viewState: CustomerOverviewViewState,
        checkedCustomer: (Int) -> Unit,
        navigateToNotification: () -> Unit,
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
                        onClick = { checkedCustomer(user.id) },
                        checkedCustomer = checkedCustomer,
                    )
                }
            }

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.send_notification),
                onClick = { navigateToNotification() }
            )
        }
    }

    @Composable
    fun CustomerItem(
        item: User,
        onClick: () -> Unit,
        checkedCustomer: Boolean,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircleImage(imageRes = Res.drawable.poa, imageSize = 64.dp)

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
}

// region previews
@Preview
@Composable
fun CustomerOverviewScreenContentPreview() {
    CustomerOverviewScreen(partnerId = 1).PreviewContent(
        viewState = CustomerOverviewViewState(
        ),
    )
}
// endregion