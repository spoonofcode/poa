package com.spoonofcode.poa.feature.profile

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.profile.di.profileTestModule
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewAction
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewModel
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewState
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileEditViewModelest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileEditViewModel
    private lateinit var userRepository: UserRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(profileTestModule)
        super.setup()
        userRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ProfileEditViewState(),
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
                expected = ProfileEditViewState(
                    screenState = ScreenState.CONTENT,
                    profile = PROFILE_1
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            userRepository.readUserProfile(any())
        }
    }

    @Test
    fun `change custom link`() = runTest {
        val customLink = "New custom link"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileEditViewAction.InitView)

            assertEquals(
                expected = ProfileEditViewState(
                    screenState = ScreenState.CONTENT,
                    profile = PROFILE_1
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProfileEditViewAction.ChangeCustomLink(customLink))

            assertEquals(
                expected = ProfileEditViewState(
                    screenState = ScreenState.CONTENT,
                    profile = PROFILE_1.copy(customLink = customLink)
                ),
                actual = awaitItem()
            )
        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `save changes`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(ProfileEditViewAction.InitView)

            assertEquals(
                expected = ProfileEditViewState(
                    screenState = ScreenState.CONTENT,
                    profile = PROFILE_1
                ),
                actual = awaitItem()
            )

            viewModel.onAction(ProfileEditViewAction.SaveChanges)

            assertEquals(
                expected = ProfileEditViewState(
                    screenState = ScreenState.LOADING,
                    profile = PROFILE_1
                ),
                actual = awaitItem()
            )
        }

//        verifySuspend {
//            productRepository.read(PRODUCT_1.id)
//        }
//
//        verifySuspend {
//            productRepository.update(any(), any())
//        }

        verifySuspend {
            viewModelNavigator.pop()
        }
    }
}