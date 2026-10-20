package com.spoonofcode.feature.tac.presentation

import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.tac.data.test.TACMockData
import com.spoonofcode.feature.tac.domain.repository.TACRepository
import com.spoonofcode.feature.tac.presentation.di.tacPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TACViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: TACViewModel
    private lateinit var tacRepository: TACRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(tacPresentationTestModule)
        super.beforeTest()
        tacRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            assertEquals(
                expected = TACViewState(
                    isPrivacyPolicyAccepted = TACMockData.SETTINGS_1.isPrivacyPolicyAccepted,
                    isTermsAndConditionsAccepted = TACMockData.SETTINGS_1.isTermsAndConditionsAccepted,
                    privacyPolicyLink = TACMockData.SETTINGS_1.privacyPolicyLink,
                    marketingLink = TACMockData.SETTINGS_1.marketingLink,
                ),
                actual = viewState.awaitContentState()
            )
        }
    }

    @Test
    fun `initial view - error`() = runTest {
        everySuspend { tacRepository.getTACSettings() } returns Result.failure(Exception("Backend exception"))

        val expectedMessage = UiText.Resource(Res.string.default_error)

        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            viewState.awaitErrorState()
            assertEquals(
                expected = expectedMessage,
                actual = snackbarEvent.awaitItem().message
            )
        }
    }

    @Test
    fun `change privacy policy`() = runTest {
        runVmTest(viewModel) {
            onAction(TACViewAction.ChangePrivacyPolicy(accepted = false))

            with(viewState.awaitContentState()) {
                assertFalse(isPrivacyPolicyAccepted)
            }

        }
    }

    @Test
    fun `change terms and conditions`() = runTest {
        runVmTest(viewModel) {
            onAction(TACViewAction.ChangeTermsAndConditions(accepted = false))

            with(viewState.awaitContentState()) {
                assertFalse(isTermsAndConditionsAccepted)
            }
        }
    }

    @Test
    fun `save changes`() = runTest {
        runVmTest(viewModel) {
            onAction(TACViewAction.SaveChanges)
            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }

            verifySuspend { viewModelNavigator.pop() }
        }
    }

    @Test
    fun `save changes - backend error`() = runTest {
        val expectedMessage = UiText.Resource(Res.string.default_error)
        everySuspend {
            tacRepository.setTACSettings(
                isPrivacyPolicyAccepted = any(),
                isTermsAndConditionsAccepted = any()
            )
        } returns Result.failure(Exception("Backend exception"))

        runVmTest(viewModel) {
            onAction(TACViewAction.SaveChanges)
            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }

            with(viewState.awaitContentState()) {
                assertFalse(isSaveChangesButtonLoading)
            }

            assertEquals(
                expected = expectedMessage,
                actual = snackbarEvent.awaitItem().message,
            )
        }
    }
}
