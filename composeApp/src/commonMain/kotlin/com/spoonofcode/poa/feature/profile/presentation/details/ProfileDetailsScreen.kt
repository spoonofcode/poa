package com.spoonofcode.poa.feature.profile.presentation.details

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Cards
import com.spoonofcode.poa.core.ui.compose.Images
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.edit_profile
import com.spoonofcode.poa.resources.faq
import com.spoonofcode.poa.resources.logout
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.notifications
import com.spoonofcode.poa.resources.partner_panel
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.profile
import com.spoonofcode.poa.resources.terms_and_conditions
import org.jetbrains.compose.resources.stringResource

internal class ProfileDetailsScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileDetailsViewModel, ProfileDetailsViewState, ProfileDetailsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.profile

    override fun provideInitAction(onAction: (ProfileDetailsViewAction) -> Unit): () -> Unit =
        { onAction(ProfileDetailsViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProfileDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewState: ProfileDetailsViewState,
        onAction: (ProfileDetailsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val profile = requireNotNull(viewState.profile)

            // 1. Profile Header
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Images.CircleImage(
                        imageRes = Res.drawable.poa,
                        modifier = Modifier.size(92.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Texts.HSB(
                        text = profile.name,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Buttons.PrimaryButton(
                        text = stringResource(Res.string.edit_profile),
                        fillMaxWidth = false,
                        onClick = { onAction(ProfileDetailsViewAction.NavigateToEditProfile) },
                    )
                }
            }

            // 2. Stats Row
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStatItem(
                    label = stringResource(Res.string.my_products),
                    value = profile.numberOfProductsOwnedByUser.toString()
                )
            }

            // 3. Main Actions
            Cards.ElevatedCard {
                ChevronItem(
                    icon = Icons.Default.Person,
                    title = stringResource(Res.string.partner_panel),
                    onClick = { onAction(ProfileDetailsViewAction.NavigateToPartnerPanel) }
                )
            }

            Spacers.VerticalBetweenFields()

            // 4. Settings Actions
            Cards.ElevatedCard {
                ChevronItem(
                    icon = Icons.Default.Notifications,
                    title = stringResource(Res.string.notifications),
                    onClick = { onAction(ProfileDetailsViewAction.NavigateToNotificationSettings) }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.Description,
                    title = stringResource(Res.string.terms_and_conditions),
                    onClick = { onAction(ProfileDetailsViewAction.NavigateToTAC) }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.AutoMirrored.Filled.Help,
                    title = stringResource(Res.string.faq),
                    onClick = { onAction(ProfileDetailsViewAction.NavigateToFAQ) }
                )
            }

            Spacers.VerticalBetweenFields()

            // 5. Logout
            Cards.ElevatedCard {
                ChevronItem(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    title = stringResource(Res.string.logout),
                    titleColor = MaterialTheme.colorScheme.error,
                    iconTint = MaterialTheme.colorScheme.error,
                    onClick = { onAction(ProfileDetailsViewAction.Logout) }
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }

    @Composable
    private fun ProfileStatItem(label: String, value: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Texts.TLB(text = value)
            Texts.LM(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun ChevronItem(
    icon: ImageVector,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = RoundedCornerShape(10.dp),
            color = iconTint.copy(alpha = 0.15f) // Lekko mocniejszy kolor tła ikony
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Texts.BLB(
                text = title,
                color = titleColor,
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun DividerItem() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

// region previews
@Preview
@Composable
private fun ProfileScreenContentPreview() {
    ProfileDetailsScreen().PreviewContent(
        ProfileDetailsViewState(
            profile = Profile(
                name = "Bartosz luczak (Lycha)",
                numberOfProductsOwnedByUser = 2,
                customLink = "https://example.com"
            ),
        )
    )
}
// endregion
