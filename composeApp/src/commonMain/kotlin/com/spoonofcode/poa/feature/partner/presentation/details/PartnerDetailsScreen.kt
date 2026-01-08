package com.spoonofcode.poa.feature.partner.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.SocialIcons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partner
import org.jetbrains.compose.ui.tooling.preview.Preview


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
            if (viewState.partner != null) {
                CoilImage(
                    imageModel = { viewState.partner.imageLink },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacers.VerticalBetweenFields()
                Texts.HSB(viewState.partner.description)
                Spacers.VerticalBetweenFields()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialIcons(
                        websiteUrl = viewState.partner.websiteLink,
                        instagramUrl = viewState.partner.instagramLink,
                        facebookUrl = viewState.partner.facebookLink,
                        youtubeUrl = viewState.partner.youtubeLink,
                        xUrl = viewState.partner.xLink,
                    )
                }
                Spacers.VerticalBetweenFields()
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