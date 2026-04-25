package com.spoonofcode.feature.profile.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
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

        verifySuspend { routeResolver.resolve(AuthModuleRoute.Login) }
        verifySuspend { viewModelNavigator.replaceAll(any()) }
    }

    @Test
    fun `navigates to edit profile screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToEditProfile)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(ProfileModuleRoute.ProfileEdit) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to faq screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToFAQ)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(FAQModuleRoute.FAQ) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to notification screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToNotificationSettings)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationSettings) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to partner panel screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToPartnerPanel)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(PartnerModuleRoute.PartnerPanel) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

    @Test
    fun `navigates to tac screen`() = runTest {
        viewModel.onAction(ProfileDetailsViewAction.NavigateToTAC)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(TACModuleRoute.TAC) }
        verifySuspend { viewModelNavigator.push(any()) }
    }

}