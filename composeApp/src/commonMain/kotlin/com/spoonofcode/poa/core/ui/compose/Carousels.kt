package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.poa
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class CarouselSportEventItem(
    // TODO Now set static image, later change to loaded image from backend
    val imageResId: DrawableResource = Res.drawable.poa,
    val sportEventId: Int,
    val title: String,
    val rangeDateTime: String,
)

object Carousels {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomCarousel(
        title: String,
        items: List<CarouselSportEventItem>,
        onItemClick: (sportEventId: Int) -> Unit,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Texts.HSB(
                text = title,
            )
            Spacers.VerticalBetweenFields()
            HorizontalUncontainedCarousel(
                state = rememberCarouselState { items.count() },
                modifier = Modifier.fillMaxWidth(),
                itemWidth = 186.dp,
            ) { i ->
                val item = items[i]
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(221.dp)
                        .padding(16.dp)
                        .clickable {
                            onItemClick(item.sportEventId)
                        },
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(resource = item.imageResId),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Texts.BLB(
                                text = item.title,
                            )
                            Spacers.VerticalBetweenFields()
                            Texts.BM(
                                text = item.rangeDateTime,
                            )
                        }
                    }
                }
            }
            Spacers.VerticalBetweenFields()
        }
    }
}

//@Composable
//private fun SportEventImage(type: Type) {
//    val imageTrainingType = when (type.id) {
//        Types.DOJO_BOXING_TRAINING.id -> Res.drawable.dojo_boxing_training
//        Types.DOJO_GRAPPLING_TRAINING.id -> Res.drawable.dojo_grappling_training
//        Types.DOJO_YOUTH_TRAINING.id -> Res.drawable.dojo_youth_training
//        Types.DOJO_BEGINNERS_GROUP_TRAINING.id -> Res.drawable.dojo_beginners_group_training
//        Types.DOJO_BEGINNERS_TRAINING.id -> Res.drawable.dojo_beginners_training
//        Types.DOJO_INDIVIDUAL_TRAINING.id -> Res.drawable.dojo_individual_training
//        Types.DOJO_MOTOR_TRAINING.id -> Res.drawable.dojo_motor_training
//        Types.DOJO_OPEN_TRAINING.id -> Res.drawable.dojo_open_training
//        Types.DOJO_MAT_TRAINING.id -> Res.drawable.dojo_mat_training
//        Types.DOJO_ADVANCED_GROUP_TRAINING.id -> Res.drawable.dojo_advanced_group_training
//        else -> Res.drawable.dojo_training
//    }
//
//    Image(
//        painter = painterResource(resource = imageTrainingType),
//        contentDescription = null,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(16.dp)),
//        contentScale = ContentScale.Crop
//    )
//    Spacers.VerticalBetweenFields()
//}

