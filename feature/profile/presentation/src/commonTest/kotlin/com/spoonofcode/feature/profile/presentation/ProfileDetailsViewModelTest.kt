package com.spoonofcode.feature.profile.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.faq.presentation.FAQScreen
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelScreen
import com.spoonofcode.feature.profile.di.profileTestModule
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewAction
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewState
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen
import com.spoonofcode.feature.tac.presentation.TACScreen
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileDetailsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(profileTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            val initialState = awaitItem()
            assertEquals(
                expected = ViewState(
                    data = ProfileDetailsViewState(),
                    screenState = ScreenState.LOADING
                ),
                actual = initialState
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileDetailsViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProfileDetailsViewState(profile = PROFILE_1),
                    screenState = ScreenState.CONTENT
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `logout success`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.Logout)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.replaceAll(containsTypes(LoginScreen::class)) }
    }

    @Test
    fun `navigates to edit profile screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToEditProfile)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<ProfileEditScreen>()) }
    }

    @Test
    fun `navigates to faq screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToFAQ)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<FAQScreen>()) }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToNotificationSettings)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<NotificationSettingsScreen>()) }
    }

    @Test
    fun `navigates to partner panel screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToPartnerPanel)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<PartnerPanelScreen>()) }
    }

    @Test
    fun `navigates to tac screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToTAC)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<TACScreen>()) }
    }

}