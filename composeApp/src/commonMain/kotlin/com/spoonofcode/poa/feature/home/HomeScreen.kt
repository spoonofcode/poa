@file:OptIn(ExperimentalTime::class)

package com.spoonofcode.poa.feature.home

import kotlin.time.ExperimentalTime
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.ImageSlider
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.home
import com.spoonofcode.poa.resources.new_partners
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.model.RewardStatus
import com.spoonofcode.poa.resources.check_in_again
import com.spoonofcode.poa.resources.collect_reward
import com.spoonofcode.poa.resources.daily_check_in
import com.spoonofcode.poa.resources.day_number
import com.spoonofcode.poa.resources.hours
import com.spoonofcode.poa.resources.minutes
import com.spoonofcode.poa.resources.next_check_in
import com.spoonofcode.poa.resources.points_short
import com.spoonofcode.poa.resources.seconds
import com.spoonofcode.poa.resources.streak_days_count
import com.spoonofcode.poa.resources.total_points
import com.spoonofcode.poa.resources.your_streak
import kotlin.time.Duration.Companion.hours

internal class HomeScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<HomeViewModel, HomeViewState>() {

    override fun provideTopAppBarTitle() = Res.string.home

    override fun provideTopBarActions(
        viewModel: HomeViewModel?,
    ) = listOf(
        TopBarAction(
            icon = Icons.Default.Notifications,
            description = "Notification",
            badgeCount = viewModel?.getNumberOfNewNotifications(),
        ) { viewModel?.navigateToNotifications() },
    )

    override fun provideInitAction(viewModel: HomeViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<HomeViewModel>()

    @Composable
    override fun provideContent(
        viewModel: HomeViewModel?,
        viewState: HomeViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        onCheckInClicked = { viewModel?.onCheckInClicked() },
    )
}

@Composable
internal fun ContentView(
    viewState: HomeViewState,
    onCheckInClicked: () -> Unit,
): @Composable (ColumnScope.() -> Unit) {
    return {
        ImageSlider(
            label = stringResource(Res.string.new_partners),
            imageLinks = viewState.partnerImageLinks,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DailyCheckInSection(
            rewards = viewState.rewards,
            totalPoints = viewState.totalPoints,
            nextCheckInTimeMillis = viewState.nextCheckInTimeMillis,
            onCheckInClicked = onCheckInClicked
        )
    }
}
@Composable
internal fun DailyCheckInSection(
    rewards: List<DailyReward>,
    totalPoints: Int,
    nextCheckInTimeMillis: Long?,
    onCheckInClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val streak = rewards.count { it.status == RewardStatus.CLAIMED }
    val isActionAvailable = rewards.any { it.status == RewardStatus.AVAILABLE_TO_CLAIM }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.daily_check_in),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(Res.string.your_streak),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Text(
                    text = "$streak ${stringResource(Res.string.streak_days_count)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(Res.string.total_points),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Text(
                    text = "$totalPoints ${stringResource(Res.string.points_short)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(rewards) { reward ->
                RewardItem(reward = reward)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (nextCheckInTimeMillis != null) {
            Text(
                stringResource(Res.string.next_check_in),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            CountdownTimer(targetTimeMillis = nextCheckInTimeMillis)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckInClicked,
            enabled = isActionAvailable,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(Res.string.collect_reward))
        }
    }
}
@Composable
internal fun CountdownTimer(targetTimeMillis: Long) {
    var remainingTime by remember { mutableStateOf(0L) }

    LaunchedEffect(targetTimeMillis) {
        while (true) {
            val currentTime = Clock.System.now().toEpochMilliseconds()
            val newRemainingTime = (targetTimeMillis - currentTime).coerceAtLeast(0)

            remainingTime = newRemainingTime

            if (newRemainingTime <= 0) {
                break
            }

            delay(1000L)
        }
    }

    val totalSeconds = remainingTime / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    if (remainingTime > 0) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CountdownUnit(value = hours, label = stringResource(Res.string.hours))
            CountdownUnit(value = minutes, label = stringResource(Res.string.minutes))
            CountdownUnit(value = seconds, label = stringResource(Res.string.seconds))
        }
    } else {
        Text(
            text = stringResource(Res.string.check_in_again),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun RewardItem(reward: DailyReward) {
    val isAvailable = reward.status == RewardStatus.AVAILABLE_TO_CLAIM
    val isClaimed = reward.status == RewardStatus.CLAIMED

    val backgroundColor = when (reward.status) {
        RewardStatus.CLAIMED -> Color(0xFF3C3C3C)
        RewardStatus.AVAILABLE_TO_CLAIM -> MaterialTheme.colorScheme.primary
        RewardStatus.LOCKED -> Color(0xFF2E2E2E)
    }
    val border = if (isAvailable) BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary) else null
    val contentAlpha = if (reward.status == RewardStatus.LOCKED) 0.5f else 1f

    Card(
        modifier = Modifier.size(width = 80.dp, height = 100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = border
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp).alpha(contentAlpha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(Res.string.day_number, reward.day),
                fontSize = 12.sp
            )

            val icon = if (isClaimed) Icons.Default.CheckCircle else Icons.Default.CardGiftcard
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(28.dp))
            Text(
                text = "${reward.points} ${stringResource(Res.string.points_short)}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CountdownUnit(value: Long, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFF2E2E2E), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(text = label, fontSize = 10.sp, color = Color.Gray)
    }
}
// region previews
@Preview
@Composable
private fun HomeScreenContentPreview() {
    val rewards = (1..5).map { day ->
        DailyReward(
            day = day,
            points = 10 + (day * 5),
            status = if (day < 5) RewardStatus.CLAIMED else RewardStatus.AVAILABLE_TO_CLAIM
        )
    }
    HomeScreen().PreviewContent(
        HomeViewState(rewards = rewards)
    )
}

@Preview
@Composable
private fun HomeScreenContentCheckedInPreview() {
    val rewards = (1..6).map { day ->
        DailyReward(
            day = day,
            points = 10 + (day * 5),
            status = RewardStatus.CLAIMED
        )
    }
    HomeScreen().PreviewContent(
        HomeViewState(
            rewards = rewards,
            nextCheckInTimeMillis = Clock.System.now().toEpochMilliseconds() + 5.hours.inWholeMilliseconds
        )
    )
}
// endregion