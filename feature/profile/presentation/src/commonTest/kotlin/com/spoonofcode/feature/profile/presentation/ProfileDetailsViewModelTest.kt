package com.spoonofcode.feature.profile.presentation

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.FAQModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.profile.data.test.ProfileMockData.PROFILE_1
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewAction
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewState
import com.spoonofcode.feature.profile.presentation.di.profilePresentationTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfileDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileDetailsViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profilePresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `init view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            assertEquals(
                expected = ProfileDetailsViewState(
                    profile = PROFILE_1,
                    partnerPanelVisible = true,
                ),
                actual = viewState.awaitContentState()
            )
        }
    }

    @Test
    fun `logout success`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.Logout)
            verifySuspend { viewModelNavigator.push(screen = AuthModuleRoute.Login) }
        }
    }

    @Test
    fun `navigates to edit profile screen`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.NavigateToEditProfile)
            verifySuspend { viewModelNavigator.push(screen = ProfileModuleRoute.ProfileEdit) }
        }
    }

    @Test
    fun `navigates to faq screen`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.NavigateToFAQ)
            verifySuspend { viewModelNavigator.push(screen = FAQModuleRoute.FAQ) }
        }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.NavigateToNotificationSettings)
            verifySuspend { viewModelNavigator.push(screen = NotificationModuleRoute.NotificationSettings) }
        }
    }

    @Test
    fun `navigates to partner panel screen`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.NavigateToPartnerPanel)
            verifySuspend { viewModelNavigator.push(screen = PartnerModuleRoute.PartnerPanel) }
        }
    }

    @Test
    fun `navigates to tac screen`() = runTest {
        runVmTest(viewModel) {
            onAction(ProfileDetailsViewAction.NavigateToTAC)
            verifySuspend { viewModelNavigator.push(screen = TACModuleRoute.TAC()) }
        }
    }
}