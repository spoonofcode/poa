package com.spoonofcode.feature.profile.presentation

import app.cash.turbine.test
import com.spoonofcode.core.presentation.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.profile.di.profileTestModule
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewAction
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewState
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileEditViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileEditViewModel
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(profileTestModule)
        super.setup()
        profileRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view success`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileEditViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(
                        profile = PROFILE_1
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            profileRepository.readUserProfile()
        }
    }

    @Test
    fun `change custom link`() = runTest {
        val customLink = "New custom link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileEditViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(
                        profile = PROFILE_1
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProfileEditViewAction.ChangeCustomLink(customLink))

            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(
                        profile = PROFILE_1.copy(customLink = customLink)
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `save changes`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileEditViewAction.InitView)

            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(
                        profile = PROFILE_1
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProfileEditViewAction.SaveChanges)

            assertEquals(
                expected = ViewState(
                    data = ProfileEditViewState(
                        profile = PROFILE_1
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        advanceUntilIdle()

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}