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
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.compose.CircleImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.edit_profile
import com.spoonofcode.poa.resources.faq
import com.spoonofcode.poa.resources.logout
import com.spoonofcode.poa.resources.notifications
import com.spoonofcode.poa.resources.partner_panel
import com.spoonofcode.poa.resources.poa
import com.spoonofcode.poa.resources.profile
import com.spoonofcode.poa.resources.terms_and_conditions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProfileScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProfileViewModel, ProfileViewState>() {

    override fun provideTopAppBarTitle() = Res.string.profile

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
        navigateToEditProfile = { viewModel?.navigateToEditProfile() },
        navigateToPartnerPanel = { viewModel?.navigateToPartnerPanel() },
        navigateToFAQ = { viewModel?.navigateToFAQ() },
    )

    @Composable
    private fun ContentView(
        viewState: ProfileViewState,
        navigateToEditProfile: () -> Unit,
        navigateToPartnerPanel: () -> Unit,
        navigateToFAQ: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                CircleImage(imageRes = Res.drawable.poa)
            }

            Spacers.VerticalBetweenFields()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Texts.HS(viewState.profile!!.name)
            }

            Spacers.VerticalBetweenFields()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                FilledTonalButton(
                    modifier = Modifier.heightIn(min = 48.dp),
                    shape = MaterialTheme.shapes.medium,
                    onClick = navigateToEditProfile,
                ) {
                    Text(stringResource(resource = Res.string.edit_profile))
                }
            }

            Spacers.VerticalBetweenFields()

            // Section 1
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.Person,
                    title = stringResource(resource = Res.string.partner_panel),
                    onClick = navigateToPartnerPanel
                )
            }

            Spacer(Modifier.height(12.dp))

            // Section 2
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.Notifications,
                    title = stringResource(resource = Res.string.notifications),
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.Description,
                    title = stringResource(resource = Res.string.terms_and_conditions),
                    onClick = { }
                )
                DividerItem()
                ChevronItem(
                    icon = Icons.Default.Help,
                    title = stringResource(resource = Res.string.faq),
                    onClick = navigateToFAQ
                )
            }

            Spacer(Modifier.height(12.dp))

            // Logout
            SectionCard {
                ChevronItem(
                    icon = Icons.Default.Logout,
                    title = stringResource(resource = Res.string.logout),
                    titleColor = MaterialTheme.colorScheme.error,
                    iconTint = MaterialTheme.colorScheme.error,
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
    HorizontalDivider(
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