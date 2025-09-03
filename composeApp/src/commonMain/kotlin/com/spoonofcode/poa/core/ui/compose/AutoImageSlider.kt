package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun ImageSlider(
    label: String,
    imageLinks: List<String>,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 8.dp,
    indicatorSpacing: Dp = 8.dp
) {
    require(imageLinks.isNotEmpty())

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageLinks.size }
    )
    Texts.BLB(label)
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState
            ),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        ) { page ->
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { imageLinks[page] },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(imageLinks.size) { idx ->
                val isSelected = pagerState.currentPage == idx
                Box(
                    modifier = Modifier
                        .padding(horizontal = indicatorSpacing / 2)
                        .size(if (isSelected) indicatorSize * 1.4f else indicatorSize)
                        .clip(CircleShape)
                        .background(
                            if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                        )
                )
            }
        }
    }
}