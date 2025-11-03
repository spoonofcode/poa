@file:OptIn(ExperimentalTime::class)

package com.spoonofcode.poa.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.model.RewardStatus
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.CountdownTimer
import com.spoonofcode.poa.core.ui.compose.ImageSlider
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.daily_check_in
import com.spoonofcode.poa.resources.day_number
import com.spoonofcode.poa.resources.home
import com.spoonofcode.poa.resources.new_partners
import com.spoonofcode.poa.resources.next_check_in
import com.spoonofcode.poa.resources.points_short
import com.spoonofcode.poa.resources.streak_days_count
import com.spoonofcode.poa.resources.total_points
import com.spoonofcode.poa.resources.your_streak
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

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
        Texts.HMB(
            text = stringResource(Res.string.daily_check_in),
        )

        Spacers.VerticalBetweenFields()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Texts.LM(stringResource(Res.string.your_streak))
                Texts.TMB("$streak ${stringResource(Res.string.streak_days_count)}")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Texts.LM(stringResource(Res.string.total_points))
                Texts.TMB("$totalPoints ${stringResource(Res.string.points_short)}")
            }
        }

        Spacers.VerticalBetweenFields()

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(rewards) { reward ->
                RewardItem(reward = reward)
            }
        }

        Spacers.VerticalBetweenFields()

        if (nextCheckInTimeMillis != null) {
            Text(
                stringResource(Res.string.next_check_in),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            CountdownTimer(targetTimeMillis = nextCheckInTimeMillis)
        }

        Spacers.VerticalBetweenFields()

        Buttons.PrimaryButton(
            text = stringResource(Res.string.daily_check_in),
            onClick = onCheckInClicked,
            enabled = isActionAvailable,
        )
    }
}

@Composable
private fun RewardItem(reward: DailyReward) {
    val isClaimed = reward.status == RewardStatus.CLAIMED
    val cointerColor = if (reward.status == RewardStatus.AVAILABLE_TO_CLAIM) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified

    ElevatedCard(
        modifier = Modifier.size(width = 80.dp, height = 100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cointerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Texts.LM(stringResource(Res.string.day_number, reward.day))

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
            screenState = ScreenState.CONTENT,
            rewards = rewards,
            nextCheckInTimeMillis = Clock.System.now()
                .toEpochMilliseconds() + 5.hours.inWholeMilliseconds
        )
    )
}
// endregion