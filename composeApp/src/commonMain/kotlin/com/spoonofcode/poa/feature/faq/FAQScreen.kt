package com.spoonofcode.poa.feature.faq

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.ExpandableList
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.faq
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class FAQScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<FAQViewModel, FAQViewState>() {

    override fun provideTopAppBarTitle() = Res.string.faq

    override fun provideInitAction(viewModel: FAQViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<FAQViewModel>()

    @Composable
    override fun provideContent(
        viewModel: FAQViewModel?,
        viewState: FAQViewState
    ): @Composable (ColumnScope.() -> Unit) = ContentView(
        viewState = viewState,
    )

    @Composable
    private fun ContentView(
        viewState: FAQViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            ExpandableList(items = viewState.faqs.map { it.question to it.answer })
        }
    }

}

// region previews
@Preview
@Composable
fun FAQScreenContentPreview() {
    FAQScreen().PreviewContent(
        viewState = FAQViewState(),
    )
}
// endregion