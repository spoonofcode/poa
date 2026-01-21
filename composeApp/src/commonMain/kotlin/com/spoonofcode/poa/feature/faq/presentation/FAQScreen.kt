package com.spoonofcode.poa.feature.faq.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.ExpandableList
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.faq
import androidx.compose.ui.tooling.preview.Preview

internal class FAQScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<FAQViewModel, FAQViewState, FAQViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.faq

    override fun provideInitAction(onAction: (FAQViewAction) -> Unit): () -> Unit =
        { onAction(FAQViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<FAQViewModel>()

    @Composable
    override fun provideContent(
        viewState: FAQViewState,
        onAction: (FAQViewAction) -> Unit,
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