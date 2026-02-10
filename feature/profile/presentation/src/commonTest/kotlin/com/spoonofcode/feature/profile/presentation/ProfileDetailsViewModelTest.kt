package com.spoonofcode.feature.profile.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.appnavigation.FAQModule
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.appnavigation.ProfileModule
import com.spoonofcode.feature.appnavigation.TACModule
import com.spoonofcode.feature.profile.data.test.ProfileMockData.PROFILE_1
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewAction
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewState
import com.spoonofcode.feature.profile.presentation.di.profilePresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileDetailsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(profilePresentationTestModule)
        super.setup()
        viewModel = getSut()
    }

    @AfterTest
    override fun tearDown() {
        super.tearDown()
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

        verifySuspend { routeResolver.resolve(AuthModule.LoginScreen) }
        verifySuspend { viewModelNavigator.replaceAll(any()) }
    }

    @Test
    fun `navigates to edit profile screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToEditProfile)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProfileModule.ProfileEditScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to faq screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToFAQ)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(FAQModule.FAQScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToNotificationSettings)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModule.NotificationSettingsScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner panel screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToPartnerPanel)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(PartnerModule.PartnerPanelScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to tac screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToTAC)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(TACModule.TACScreen) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

}