package com.spoonofcode.poa.feature.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) : BaseScreen<DemoViewModel, DemoViewState, DemoViewAction>() {

//    override fun provideInitAction(onAction: (DemoViewAction) -> Unit): () -> Unit =
//        { onAction(DemoViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<DemoViewModel>()

    @Composable
    override fun provideContent(
        viewState: DemoViewState,
        onAction: (DemoViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BL(text = stringResource(resource = Res.string.demo))

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                if (viewState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Texts.BL(text = "Everything loaded.")
                }
            }

        }
    }
}

// region previews
@Preview
@Composable
private fun DemoScreenContentPreview() {
    DemoScreen().PreviewContent(
        viewState = DemoViewState(),
    )
}
// endregion