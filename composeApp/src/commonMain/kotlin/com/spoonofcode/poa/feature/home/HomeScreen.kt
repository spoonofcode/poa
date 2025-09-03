package com.spoonofcode.poa.feature.home

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.AutoImageSlider
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.home
import org.jetbrains.compose.ui.tooling.preview.Preview

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
    )

    @Composable
    private fun ContentView(
        viewState: HomeViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB("HOME")

            AutoImageSlider(
                imageLinks = listOf(
                    "https://proof-of-wear.com/images/logo.jpg",
                    "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
                    "https://proof-of-wear.com/images/logo.jpg",

                )
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