package com.spoonofcode.core.presentation.compose

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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class CarouselSportEventItem(
    val imageResId: DrawableResource,
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
