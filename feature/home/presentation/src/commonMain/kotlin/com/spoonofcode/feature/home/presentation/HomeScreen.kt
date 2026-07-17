package com.spoonofcode.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.AutoImageSlider
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.CountdownTimer
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.home.domain.model.DailyReward
import com.spoonofcode.feature.home.domain.model.DailyRewardStatus
import org.jetbrains.compose.resources.stringResource

internal class HomeScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<HomeViewModel, HomeViewState, HomeViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.home)

    @Composable
    override fun provideTopBarActions(
        onAction: (HomeViewAction) -> Unit
    ) = listOf(
        TopBarAction(
            icon = Icons.Default.Notifications,
            description = "Notification",
//            badgeCount = viewState.unreadNotificationsCount,
        ) { onAction(HomeViewAction.NavigateToNotifications) },
    )

    override fun provideInitAction(onAction: (HomeViewAction) -> Unit): () -> Unit =
        { onAction(HomeViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<HomeViewModel>()

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun provideContent(
        viewState: HomeViewState,
        onAction: (HomeViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            AutoImageSlider(
                label = stringResource(Res.string.new_partners),
                imageLinks = viewState.partnerImageLinks,
            )

            Spacers.VerticalBetweenFields()

            viewState.dailyCheckInStatus?.let { status ->
                DailyCheckInSection(
                    streak = status.streak,
                    totalPoints = viewState.totalPoints,
                    isActionAvailable = status.isAvailableNow,
                    nextCheckInTimeMillis = status.nextCheckInTimeMillis,
                    rewards = status.dailyRewards,
                    onCheckInClicked = { onAction(HomeViewAction.DailyCheckIn) }
                )
            }
        }
    }

    @Composable
    internal fun DailyCheckInSection(
        streak: Int,
        totalPoints: Int,
        rewards: List<DailyReward>,
        isActionAvailable: Boolean,
        nextCheckInTimeMillis: Long?,
        onCheckInClicked: () -> Unit,
    ) {
        Cards.ElevatedCard {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Texts.TMB(text = stringResource(Res.string.daily_check_in))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    HomeStatItem(
                        label = stringResource(Res.string.your_streak),
                        value = "$streak ${stringResource(Res.string.streak_days_count)}"
                    )
                    HomeStatItem(
                        label = stringResource(Res.string.total_points),
                        value = "$totalPoints ${stringResource(Res.string.points_short)}"
                    )
                }

                Spacers.VerticalBetweenFields()

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(rewards) { reward ->
                        DailyRewardItem(reward = reward)
                    }
                }

                Spacers.VerticalBetweenFields()

                if (nextCheckInTimeMillis != null && !isActionAvailable) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Texts.LSB(
                            text = stringResource(Res.string.next_check_in),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        CountdownTimer(targetTimeMillis = nextCheckInTimeMillis)
                    }
                    Spacers.VerticalBetweenFields()
                }

                Buttons.PrimaryButton(
                    text = stringResource(Res.string.daily_check_in),
                    onClick = onCheckInClicked,
                    enabled = isActionAvailable,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    private fun HomeStatItem(label: String, value: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Texts.TMB(text = value)
            Texts.LS(text = label)
        }
    }

    @Composable
    private fun DailyRewardItem(reward: DailyReward) {
        val isClaimed = reward.status == DailyRewardStatus.CLAIMED
        val isAvailable = reward.status == DailyRewardStatus.AVAILABLE_TO_CLAIM

        val containerColor = if (isAvailable) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        }

        Cards.ElevatedCard(
            modifier = Modifier.size(width = 85.dp, height = 110.dp),
            colors = CardDefaults.cardColors(containerColor = containerColor),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Texts.LSB(text = stringResource(Res.string.day_number, reward.day))

                Icon(
                    imageVector = if (isClaimed) Icons.Default.CheckCircle else Icons.Default.CardGiftcard,
                    contentDescription = null,
                    tint = if (isClaimed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(28.dp)
                )

                Texts.LMB(
                    text = "${reward.points} ${stringResource(Res.string.points_short)}",
                    color = if (isAvailable) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreen().PreviewContent(HomeViewState())
}
