package com.spoonofcode.feature.tac.presentation

import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.tac.data.test.TACMockData
import com.spoonofcode.feature.tac.presentation.di.tacPresentationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TACViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: TACViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(tacPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = true) {
            assertEquals(
                expected = ViewState.Content(
                    data = TACViewState(
                        isPrivacyPolicyAccepted = TACMockData.SETTINGS_1.isPrivacyPolicyAccepted,
                        isTermsAndConditionsAccepted = TACMockData.SETTINGS_1.isTermsAndConditionsAccepted,
                        privacyPolicyLink = TACMockData.SETTINGS_1.privacyPolicyLink,
                        marketingLink = TACMockData.SETTINGS_1.marketingLink,
                    )
                ),
                actual = viewState.awaitItem()
            )
        }
    }
}