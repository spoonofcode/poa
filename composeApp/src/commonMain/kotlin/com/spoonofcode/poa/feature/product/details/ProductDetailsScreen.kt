package com.spoonofcode.poa.feature.product.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schema
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
import com.spoonofcode.poa.resources.collection_name
import com.spoonofcode.poa.resources.custom_link
import com.spoonofcode.poa.resources.description
import com.spoonofcode.poa.resources.name
import com.spoonofcode.poa.resources.product
import com.spoonofcode.poa.resources.website_link
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal data class ProductDetailsScreen(
    val productId: String,
) : BaseScreen<ProductDetailsViewModel, ProductDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.product

    @Composable
    override fun provideViewModel() = koinViewModel<ProductDetailsViewModel>()

    override fun provideTopBarActions(
        viewModel: ProductDetailsViewModel,
    ) = listOf(
        TopBarAction(Icons.Default.Edit, "Edit") { viewModel.editProduct() },
        TopBarAction(Icons.Default.Delete, "Delete") { viewModel.deleteProduct() }
    )

    override fun provideReloadAction(viewModel: ProductDetailsViewModel): () -> Unit =
        { viewModel.initView(productId = productId) }

    @Composable
    override fun provideContentView(
        viewModel: ProductDetailsViewModel,
        viewState: ProductDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView(productId = productId)
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ProductDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
//            ProductAnimation(viewState.product!!.nftAnimation)

            Texts.BLB(viewState.product!!.tagId)

            ProductItemRowElement(
                label = Res.string.name,
                elementValue = viewState.product.name,
                icon = Icons.Default.Paid,
                contentDescription = "Product name",
            )

            ProductItemRowElement(
                label = Res.string.description,
                elementValue = viewState.product.description,
                icon = Icons.Default.WebStories,
                contentDescription = "Series",
            )

            ProductItemRowElement(
                label = Res.string.collection_name,
                elementValue = viewState.product.collectionName,
                icon = Icons.Default.Person,
                contentDescription = "Owner",
            )

            ProductItemRowElement(
                label = Res.string.website_link,
                elementValue = viewState.product.websiteLink!!,
                icon = Icons.Default.Mail,
                contentDescription = "Owner Mail",
            )

            ProductItemRowElement(
                label = Res.string.custom_link,
                elementValue = viewState.product.customLink!!,
                icon = Icons.Default.Schema,
                contentDescription = "Level",
            )
        }
    }

//    @Composable
//    private fun ProductImage(type: Type) {
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
    private fun ProductItemRowElement(
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
    fun ProductAnimation(productAnimation: String) {
        Box {
            LoopingVideo(
                url = productAnimation,
                modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
                mute = true      // silent background animation
            )
        }
    }
}