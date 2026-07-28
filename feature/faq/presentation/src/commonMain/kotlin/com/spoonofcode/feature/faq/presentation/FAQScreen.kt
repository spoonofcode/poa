package com.spoonofcode.feature.faq.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.compose.ExpandableListItem
import com.spoonofcode.core.presentation.compose.Texts
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun FAQScreen(
    viewModel: FAQViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        topBarTitle = stringResource(Res.string.faq),
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
    Texts.TL(text = stringResource(Res.string.faq_setion_1))
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
    Texts.TL(text = stringResource(Res.string.faq_setion_2))
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
    Texts.TL(text = stringResource(Res.string.faq_setion_3))
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
//@Preview
//@Composable
//private fun FAQScreenContentPreview() {
//    FAQScreen().PreviewContent(
//        viewState = FAQViewState(),
//    )
//}
// endregion
