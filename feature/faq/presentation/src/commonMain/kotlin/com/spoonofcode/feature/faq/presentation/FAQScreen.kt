package com.spoonofcode.feature.faq.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.ExpandableListItem
import com.spoonofcode.core.presentation.compose.TextTL
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.faq),
)

@Composable
internal fun FAQScreen(
    viewModel: FAQViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: FAQViewState,
    onAction: (FAQViewAction) -> Unit,
) {
    TextTL(text = stringResource(Res.string.faq_setion_1))
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_1),
        description = stringResource(Res.string.faq_answer_1),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_2),
        description = stringResource(Res.string.faq_answer_2),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_3),
        description = stringResource(Res.string.faq_answer_3),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_4),
        description = stringResource(Res.string.faq_answer_4),
    )
    TextTL(text = stringResource(Res.string.faq_setion_2))
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_5),
        description = stringResource(Res.string.faq_answer_5),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_6),
        description = stringResource(Res.string.faq_answer_6),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_7),
        description = stringResource(Res.string.faq_answer_7),
    )
    TextTL(text = stringResource(Res.string.faq_setion_3))
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_8),
        description = stringResource(Res.string.faq_answer_8),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_9),
        description = stringResource(Res.string.faq_answer_9),
    )
    ExpandableListItem(
        title = stringResource(Res.string.faq_question_10),
        description = stringResource(Res.string.faq_answer_10),
    )
}

// region previews
@Preview
@Composable
private fun FAQScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(FAQViewState()),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
