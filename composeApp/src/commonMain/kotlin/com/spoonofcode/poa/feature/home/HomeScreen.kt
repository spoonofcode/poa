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
import kotlin.time.Duration.Companion.hours

internal class HomeScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<HomeViewModel, HomeViewState>() {

    override fun provideTopAppBarTitle() = Res.string.home

    override fun provideTopBarActions(
        viewModel: HomeViewModel?,
    ) = listOf(
        TopBarAction(
            Icons.Default.Notifications,
            "Notification"
        ) { viewModel?.navigateToNotifications() },
    )

    override fun provideContentPadding() = PaddingValues(
        start = Paddings.screenPadding,
        top = Paddings.screenPadding,
        bottom = Paddings.screenPadding,
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
        onCheckInClicked = { viewModel?.onCheckInClicked() }
    )

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
                nextCheckInTimeMillis = viewState.nextCheckInTimeMillis,
                onCheckInClicked = onCheckInClicked
            )
        }
    }
}
@Composable
internal fun DailyCheckInSection(
    rewards: List<DailyReward>,
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
            text = "Daily Check-in",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Poniżej potrzebny jest LazyRow, upewnij się, że masz odpowiednie importy
        // import androidx.compose.foundation.lazy.LazyRow
        // import androidx.compose.foundation.lazy.items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(rewards) { reward ->
                RewardItem(reward = reward) // Upewnij się, że komponent RewardItem też jest w tym pliku
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Użycie obliczonej wartości "streak"
        Text(
            text = "Your streak: $streak days in a row!",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (nextCheckInTimeMillis != null) {
            Text("NEXT CHECK-IN IN", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            CountdownTimer(targetTimeMillis = nextCheckInTimeMillis) // Upewnij się, że CountdownTimer jest w pliku
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckInClicked,
            enabled = isActionAvailable, // Użycie obliczonej wartości
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Collect Today's Reward")
        }
    }
}
@Composable
internal fun CountdownTimer(targetTimeMillis: Long) {
    var remainingTime by remember { mutableStateOf<Long>(targetTimeMillis - Clock.System.now().toEpochMilliseconds()) }

    LaunchedEffect(targetTimeMillis) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime = targetTimeMillis - Clock.System.now().toEpochMilliseconds()
        }
    }

    val hours = (remainingTime / (1000 * 60 * 60))
    val minutes = (remainingTime / (1000 * 60)) % 60
    val seconds = (remainingTime / 1000) % 60

    if (remainingTime > 0) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CountdownUnit(value = hours, label = "Hours")
            CountdownUnit(value = minutes, label = "Minutes")
            CountdownUnit(value = seconds, label = "Seconds")
        }
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
    // Upewnij się, że masz też te importy:
    // import androidx.compose.foundation.BorderStroke
    // import androidx.compose.ui.draw.alpha
    // import androidx.compose.material.icons.filled.CheckCircle
    // import androidx.compose.material.icons.filled.CardGiftcard
    // import androidx.compose.foundation.layout.size
    // import androidx.compose.material3.Icon
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
            Text(text = "Day ${reward.day}", fontSize = 12.sp)

            val icon = if (isClaimed) Icons.Default.CheckCircle else Icons.Default.CardGiftcard
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(28.dp))
            Text(
                text = "${reward.points} Pts",
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