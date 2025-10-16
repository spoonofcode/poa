package com.spoonofcode.poa.feature.home

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.IconWithBadge
import com.spoonofcode.poa.core.ui.compose.ImageSlider
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.home
import com.spoonofcode.poa.resources.new_partners
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            badgeCount = 5,
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
    )

    @Composable
    private fun ContentView(
        viewState: HomeViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            ImageSlider(
                label = stringResource(Res.string.new_partners),
                imageLinks = viewState.partnerImageLinks,
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreen().PreviewContent(
        HomeViewState()
    )
}
// endregion