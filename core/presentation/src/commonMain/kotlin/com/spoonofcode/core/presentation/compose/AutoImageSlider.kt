package com.spoonofcode.core.presentation.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AutoImageSlider(
    label: String? = null,
    imageLinks: List<String>,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 8.dp,
    indicatorSpacing: Dp = 8.dp,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
    autoScrollMillis: Long? = 3000L, // e.g., 3000L to auto-advance every 3s
) {
    if (imageLinks.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageLinks.size }
    )

    val scope = rememberCoroutineScope()

    if (autoScrollMillis != null && imageLinks.size > 1) {
        LaunchedEffect(autoScrollMillis, imageLinks) {
            while (true) {
                delay(autoScrollMillis)
                val next = (pagerState.currentPage + 1) % imageLinks.size
                pagerState.animateScrollToPage(next)
            }
        }
    }

    if (!label.isNullOrBlank()) {
        Texts.HS(label)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Cards.ElevatedCard(
            modifier = modifier.fillMaxWidth().height(180.dp),
        ) {
            HorizontalPager(
                state = pagerState,
            ) { page ->
                Images.ImageLink(imageLink = imageLinks[page])
            }
        }

        Spacer(Modifier.height(12.dp))

        // Animated, clickable indicators
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(imageLinks.size) { idx ->
                val selected = pagerState.currentPage == idx
                val dotSize by animateDpAsState(
                    targetValue = if (selected) indicatorSize * 1.4f else indicatorSize,
                    label = "dotSize"
                )
                val dotColor by animateColorAsState(
                    targetValue = if (selected) activeColor else inactiveColor,
                    label = "dotColor"
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = indicatorSpacing / 2)
                        .size(dotSize)
                        .clip(CircleShape)
                        .background(dotColor)
                        .clickable(
                            indication = ripple(bounded = false),
                            interactionSource = remember { MutableInteractionSource() }
                        ) { scope.launch { pagerState.animateScrollToPage(idx) } }
                )
            }
        }
    }
}
