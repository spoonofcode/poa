package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun AutoImageSlider(
    imageLinks: List<String>,
    modifier: Modifier = Modifier,
    slideIntervalMillis: Long = 5000L,
    indicatorSize: Dp = 8.dp,
    indicatorSpacing: Dp = 8.dp
) {
    require(imageLinks.isNotEmpty())

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageLinks.size }
    )

    // Auto-scroll logic: waits per page so it won't "fight" with user swipes
    LaunchedEffect(pagerState.currentPage, imageLinks.size, slideIntervalMillis) {
        // Skip auto-advance if there's only one page
        if (imageLinks.size <= 1) return@LaunchedEffect
        // Small debounce: if user just swiped, timer restarts
        delay(slideIntervalMillis)
        if (!isActive) return@LaunchedEffect
        val next = (pagerState.currentPage + 1) % imageLinks.size
        pagerState.animateScrollToPage(next)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f) // adjust to taste
        ) { page ->
            CoilImage(
                imageModel = { imageLinks[page] },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        // Dots indicator
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