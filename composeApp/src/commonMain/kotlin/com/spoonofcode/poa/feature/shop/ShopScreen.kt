package com.spoonofcode.poa.feature.shop

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.MyWebView
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partner
import com.spoonofcode.poa.resources.shop
import org.jetbrains.compose.resources.StringResource

internal class ShopScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ShopViewModel, ShopViewState>() {

    override fun provideTopAppBarTitle() = Res.string.shop
    override fun provideContentPadding() = PaddingValues()

    @Composable
    override fun provideViewModel() = koinViewModel<ShopViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ShopViewModel,
        viewState: ShopViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(viewState = viewState)
    }

    @Composable
    private fun ContentView(
        viewState: ShopViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            MyWebView(url = viewState.url)
        }
    }
}