package com.spoonofcode.poa.feature.faq.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.ExpandableListItem
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.faq
import com.spoonofcode.poa.resources.faq_answer_1
import com.spoonofcode.poa.resources.faq_answer_10
import com.spoonofcode.poa.resources.faq_answer_2
import com.spoonofcode.poa.resources.faq_answer_3
import com.spoonofcode.poa.resources.faq_answer_4
import com.spoonofcode.poa.resources.faq_answer_5
import com.spoonofcode.poa.resources.faq_answer_6
import com.spoonofcode.poa.resources.faq_answer_7
import com.spoonofcode.poa.resources.faq_answer_8
import com.spoonofcode.poa.resources.faq_answer_9
import com.spoonofcode.poa.resources.faq_question_1
import com.spoonofcode.poa.resources.faq_question_10
import com.spoonofcode.poa.resources.faq_question_2
import com.spoonofcode.poa.resources.faq_question_3
import com.spoonofcode.poa.resources.faq_question_4
import com.spoonofcode.poa.resources.faq_question_5
import com.spoonofcode.poa.resources.faq_question_6
import com.spoonofcode.poa.resources.faq_question_7
import com.spoonofcode.poa.resources.faq_question_8
import com.spoonofcode.poa.resources.faq_question_9
import com.spoonofcode.poa.resources.faq_setion_1
import com.spoonofcode.poa.resources.faq_setion_2
import com.spoonofcode.poa.resources.faq_setion_3
import org.jetbrains.compose.resources.stringResource

internal class FAQScreen() : BaseScreen<FAQViewModel, FAQViewState, FAQViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.faq

    @Composable
    override fun provideViewModel() = koinViewModel<FAQViewModel>()

    @Composable
    override fun provideContent(
        viewState: FAQViewState,
        onAction: (FAQViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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