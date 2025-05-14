package com.spoonofcode.poa.feature.nft.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.delete
import com.spoonofcode.poa.resources.edit
import com.spoonofcode.poa.resources.nft_info
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
//            NFTImage(viewState.nftInfo!!.type)

//            Texts.BLB(viewState.nftInfo!!.title)
//
//            NFTItemRowElement(
//                label = Res.string.date,
//                elementValue = viewState.nftInfo.formatRangeWithDurationInMinutes(),
//                icon = Icons.Default.Timer,
//                contentDescription = "Date and time",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.location,
//                elementValue = viewState.nftInfo.club.name,
//                icon = Icons.Default.LocationOn,
//                contentDescription = "Location",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.room,
//                elementValue = viewState.nftInfo.room.name,
//                icon = Icons.Default.MeetingRoom,
//                contentDescription = "Room",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.coach,
//                elementValue = viewState.nftInfo.creatorUser.fullName,
//                icon = Icons.Default.Person,
//                contentDescription = "Coach",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.level,
//                elementValue = viewState.nftInfo.level.name,
//                icon = Icons.Default.Leaderboard,
//                contentDescription = "Level",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.type,
//                elementValue = viewState.nftInfo.type.name,
//                icon = Icons.Default.SportsMartialArts,
//                contentDescription = "Type",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.cost,
//                elementValue = viewState.nftInfo.cost.toString(),
//                icon = Icons.Default.Paid,
//                contentDescription = "Cost",
//            )
//
//            NFTItemRowElement(
//                label = Res.string.description,
//                elementValue = viewState.nftInfo.description,
//                icon = Icons.Default.Description,
//                contentDescription = "Description",
//            )
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
}