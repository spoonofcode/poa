package com.spoonofcode.poa.feature.nft.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.Token
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.LoopingVideo
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.blockchain
import com.spoonofcode.poa.resources.contract_address
import com.spoonofcode.poa.resources.delete
import com.spoonofcode.poa.resources.edit
import com.spoonofcode.poa.resources.link
import com.spoonofcode.poa.resources.nft_info
import com.spoonofcode.poa.resources.nft_name
import com.spoonofcode.poa.resources.owner
import com.spoonofcode.poa.resources.owner_email
import com.spoonofcode.poa.resources.series
import com.spoonofcode.poa.resources.token_id
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal data class NFTInfoDetailsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.nft_info,
    val nftInfoId: String,
) : BaseScreen<NFTInfoDetailsViewModel, NFTInfoDetailsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<NFTInfoDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: NFTInfoDetailsViewModel,
        viewState: NFTInfoDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView(nftInfoId = nftInfoId)
        }

        super.reloadScreen = { viewModel.initView(nftInfoId = nftInfoId) }

        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.Edit,
                description = stringResource(resource = Res.string.edit),
                onClick = { viewModel.editNFT() }
            ),
            TopBarAction(
                icon = Icons.Default.Delete,
                description = stringResource(resource = Res.string.delete),
                onClick = { viewModel.deleteNFT() }
            ),
        )

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: NFTInfoDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            NFTAnimation(viewState.nftInfo!!.nftAnimation)

            Texts.BLB(viewState.nftInfo.tagId)

            NFTItemRowElement(
                label = Res.string.nft_name,
                elementValue = viewState.nftInfo.nftName,
                icon = Icons.Default.Paid,
                contentDescription = "NFT name",
            )

            NFTItemRowElement(
                label = Res.string.series,
                elementValue = viewState.nftInfo.series,
                icon = Icons.Default.WebStories,
                contentDescription = "Series",
            )

            NFTItemRowElement(
                label = Res.string.owner,
                elementValue = viewState.nftInfo.owner,
                icon = Icons.Default.Person,
                contentDescription = "Owner",
            )

            NFTItemRowElement(
                label = Res.string.owner_email,
                elementValue = viewState.nftInfo.ownerEmail,
                icon = Icons.Default.Mail,
                contentDescription = "Owner Mail",
            )

            NFTItemRowElement(
                label = Res.string.blockchain,
                elementValue = viewState.nftInfo.blockchain,
                icon = Icons.Default.Schema,
                contentDescription = "Level",
            )

            NFTItemRowElement(
                label = Res.string.contract_address,
                elementValue = viewState.nftInfo.contractAddress,
                icon = Icons.Default.Contacts,
                contentDescription = "Type",
            )

            NFTItemRowElement(
                label = Res.string.token_id,
                elementValue = viewState.nftInfo.tokenId,
                icon = Icons.Default.Token,
                contentDescription = "Token Id",
            )

            NFTItemRowElement(
                label = Res.string.link,
                elementValue = viewState.nftInfo.link,
                icon = Icons.Default.Link,
                contentDescription = "Link",
            )
        }
    }

//    @Composable
//    private fun NFTImage(type: Type) {
//        val imageTrainingType = when (type.id) {
//            Types.DOJO_BOXING_TRAINING.id -> Res.drawable.dojo_boxing_training
//            Types.DOJO_GRAPPLING_TRAINING.id -> Res.drawable.dojo_grappling_training
//            Types.DOJO_YOUTH_TRAINING.id -> Res.drawable.dojo_youth_training
//            Types.DOJO_BEGINNERS_GROUP_TRAINING.id -> Res.drawable.dojo_beginners_group_training
//            Types.DOJO_BEGINNERS_TRAINING.id -> Res.drawable.dojo_beginners_training
//            Types.DOJO_INDIVIDUAL_TRAINING.id -> Res.drawable.dojo_individual_training
//            Types.DOJO_MOTOR_TRAINING.id -> Res.drawable.dojo_motor_training
//            Types.DOJO_OPEN_TRAINING.id -> Res.drawable.dojo_open_training
//            Types.DOJO_MAT_TRAINING.id -> Res.drawable.dojo_mat_training
//            Types.DOJO_ADVANCED_GROUP_TRAINING.id -> Res.drawable.dojo_advanced_group_training
//            else -> Res.drawable.dojo_training
//        }
//
//        Image(
//            painter = painterResource(resource = imageTrainingType),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(16.dp)),
//            contentScale = ContentScale.Crop
//        )
//        Spacers.VerticalBetweenFields()
//    }

    @Composable
    private fun NFTItemRowElement(
        label: StringResource,
        elementValue: String,
        icon: ImageVector,
        contentDescription: String,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
            Spacers.HorizontalBetweenFields()
            Column {
                Texts.BSB(
                    text = stringResource(resource = label),
                )
                Texts.BM(
                    text = elementValue,
                )

            }
        }
        Spacers.VerticalBetweenFields()
    }

    @Composable
    fun NFTAnimation(nftAnimation: String) {
        Box {
            LoopingVideo(
                url = nftAnimation,
                modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
                mute = true      // silent background animation
            )
        }
    }
}