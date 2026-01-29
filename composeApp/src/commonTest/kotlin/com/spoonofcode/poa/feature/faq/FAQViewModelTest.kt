package com.spoonofcode.poa.feature.faq

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.FAQMockData.FAQS
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.faq.di.faqTestModule
import com.spoonofcode.feature.faq.presentation.FAQViewAction
import com.spoonofcode.feature.faq.presentation.FAQViewModel
import com.spoonofcode.feature.faq.presentation.FAQViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FAQViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: FAQViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(faqTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = FAQViewState()
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(FAQViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = FAQViewState(
                        faqs = FAQS
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}