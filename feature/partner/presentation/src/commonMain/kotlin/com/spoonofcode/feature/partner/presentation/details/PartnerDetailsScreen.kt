package com.spoonofcode.feature.partner.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.SocialIcons
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.about_partner
import com.spoonofcode.feature.partner.presentation.partner
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.partner),
)

@Composable
internal fun PartnerDetailsScreen(
    viewModel: PartnerDetailsViewModel,
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
    viewState: PartnerDetailsViewState,
    onAction: (PartnerDetailsViewAction) -> Unit,
) {
    val partner = requireNotNull(viewState.partner)

    // 1. Hero Image Card
    Cards.ElevatedCard {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {

            partner.imageLink?.let { Images.ImageLink(imageLink = it) }
        }
    }

    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 2. Name & Info
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = partner.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            partner.email?.let {
                Texts.BM(
                    text = it,
                )
            }
        }

        // 3. Description Card
        partner.description?.let { desc ->
            Cards.DescriptionCard(
                title = stringResource(Res.string.about_partner),
                description = desc
            )
        }

        // 4. Social Icons Section
        Cards.ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SocialIcons(
                    websiteUrl = partner.websiteLink,
                    instagramUrl = partner.instagramLink,
                    facebookUrl = partner.facebookLink,
                    youtubeUrl = partner.youtubeLink,
                    xUrl = partner.xLink,
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerDetailsScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerDetailsViewState(
                partner = Partner(
                    id = "550e8400-e29b-41d4-a716-446655440001",
                    name = "Beauty Saute",
                    partnerCategory = "Regular",
                    email = "beauty.saute@gmail.com",
                    description = "Beauty Sautè to więcej niż odzież – to filozofia.",
                    imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
                    websiteLink = "https://beautysaute.pl/",
                    instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                    facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
                    youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                    xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                )
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
