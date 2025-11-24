package com.spoonofcode.poa.feature.demo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.demo
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class DemoScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<DemoViewModel, DemoViewState>() {

    override fun provideInitAction(viewModel: DemoViewModel?): () -> Unit =
        { viewModel?.onAction(DemoViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<DemoViewModel>()

    @Composable
    override fun provideContent(
        viewModel: DemoViewModel?,
        viewState: DemoViewState,
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
    )

    @Composable
    internal fun ContentView(
        viewState: DemoViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BL(text = stringResource(resource = Res.string.demo))
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        ContentView(
            viewState = DemoViewState(),
        )
    }
}

// region previews
@Preview
@Composable
private fun DemoScreenContentPreview() {
    DemoScreen().PreviewContent(
        DemoViewState()
    )
}
// endregion