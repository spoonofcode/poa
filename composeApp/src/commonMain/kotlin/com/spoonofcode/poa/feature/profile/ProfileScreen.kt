package com.spoonofcode.poa.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.profile
import com.spoonofcode.poa.resources.reload
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProfileScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileViewModel, ProfileViewState>() {

    override fun provideTopAppBarTitle() = Res.string.profile

    override fun provideTopBarActions(
        viewModel: ProfileViewModel?,
    ) = listOf(
        TopBarAction(
            icon = Icons.Default.Settings,
            description = "settings",
            onClick = { viewModel?.navigateToSettings() }
        ),
    )

    override fun provideInitAction(viewModel: ProfileViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProfileViewModel?,
        viewState: ProfileViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        navigateToPartnerPanel = { viewModel?.navigateToPartnerPanel() },
    )

    @Composable
    private fun ContentView(
        viewState: ProfileViewState,
        navigateToPartnerPanel: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                RoundedImage(imageRes = Res.drawable.poa)
                Spacers.VerticalBetweenFields()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Texts.HS(viewState.profile!!.name)
                Spacers.VerticalBetweenFields()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                FilledTonalButton(
                    onClick = { },
                ) {
                    Text(stringResource(resource = Res.string.reload))
                }

                Spacers.VerticalBetweenFields()
            }

            // Section 1
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.ReceiptLong,
                    title = "My Orders",
                    onClick = { }
                )
            }

            Spacer(Modifier.height(12.dp))

            // Section 2
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.AccountCircle,
                    title = "My Details",
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.Home,
                    title = "Address Book",
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.CreditCard,
                    title = "Payment Methods",
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    onClick = { }
                )
            }

            Spacer(Modifier.height(12.dp))

            // Section 3
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.Help,
                    title = "FAQs",
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.HeadsetMic,
                    title = "Help Center",
                    onClick = { }
                )
            }

            Spacer(Modifier.height(12.dp))

            // Logout
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.ReceiptLong, // swap to your logout icon
                    title = "Logout",
                    titleColor = MaterialTheme.colorScheme.error,
                    iconTint = MaterialTheme.colorScheme.error,
                    trailing = {
                        // keep layout consistent: show chevron faintly
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.alpha(0.0f) // visually hide while keeping spacing
                        )
                    },
                    onClick = { }
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}


// --- Building blocks ---------------------------------------------------------

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        tonalElevation = 1.dp,
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            content()
        }
    }
}

@Composable
private fun ChevronItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    trailing: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    },
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .padding(start = 8.dp, end = 12.dp)
                .size(22.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        if (trailing != null) {
            Box(Modifier.padding(horizontal = 4.dp)) { trailing() }
        }
    }
}

@Composable
private fun DividerItem() {
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 0.6.dp
    )
}

// region previews
@Preview
@Composable
private fun ProfileScreenContentPreview() {
    ProfileScreen().PreviewContent(
        ProfileViewState(
            screenState = ScreenState.CONTENT,
            profile = Profile(
                name = "Bartosz luczak (Lycha)",
                numberOfProductsOwnedByUser = 2
            ),
        )
    )
}
// endregion