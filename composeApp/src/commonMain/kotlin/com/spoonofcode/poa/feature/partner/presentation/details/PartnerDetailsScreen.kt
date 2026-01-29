package com.spoonofcode.poa.feature.partner.presentation.details

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
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.SocialIcons
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.about_partner
import com.spoonofcode.core.presentation.partner
import org.jetbrains.compose.resources.stringResource


internal class PartnerDetailsScreen(
    private val partnerId: String,
) : BaseScreen<PartnerDetailsViewModel, PartnerDetailsViewState, PartnerDetailsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideInitAction(onAction: (PartnerDetailsViewAction) -> Unit): () -> Unit =
        { onAction(PartnerDetailsViewAction.InitView(partnerId = partnerId)) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerDetailsViewState,
        onAction: (PartnerDetailsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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
                    Images.ImageLink(imageLink = partner.imageLink!!)
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
    }
}

// region previews
@Preview
@Composable
fun PartnerDetailsScreenContentPreview() {
    PartnerDetailsScreen(
        partnerId = "1"
    ).PreviewContent(
        viewState = PartnerDetailsViewState(),
    )
}
// endregion
