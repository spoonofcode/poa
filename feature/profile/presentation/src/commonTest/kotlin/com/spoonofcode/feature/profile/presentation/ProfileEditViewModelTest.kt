package com.spoonofcode.feature.profile.presentation

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.profile.data.test.ProfileMockData.PROFILE_1
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.feature.profile.presentation.di.profilePresentationTestModule
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewAction
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

class ProfileEditViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: ProfileEditViewModel
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(profilePresentationTestModule)
        super.beforeTest()
        profileRepository = getKoin().get()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(PROFILE_1, profile)
                    assertTrue(isSaveChangesButtonEnable)
                    assertTrue(isViewEnable)
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { profileRepository.readUserProfile() } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `change email`() =
        runTest {
            val newEmail = "new.email@gmail.com"

            runVmTest(viewModel) {
                onAction(ProfileEditViewAction.ChangeCustomLink(link = newEmail))

                with(viewState.awaitContentState()) {
                    assertEquals(newEmail, profile.email)
                    assertTrue(isSaveChangesButtonEnable)
                }
            }
        }

    @Test
    fun `change email to invalid - save changes button disabled`() =
        runTest {
            runVmTest(viewModel) {
                onAction(ProfileEditViewAction.ChangeCustomLink(link = "invalid-email"))

                with(viewState.awaitContentState()) {
                    assertEquals("invalid-email", profile.email)
                    assertFalse(isSaveChangesButtonEnable)
                }
            }
        }

    @Test
    fun `save changes success`() =
        runTest {
            everySuspend { profileRepository.updateUserProfile() } returns Result.success(Unit)

            runVmTest(viewModel) {
                onAction(ProfileEditViewAction.SaveChanges)

                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }

                verifySuspend { profileRepository.updateUserProfile() }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `save changes error`() =
        runTest {
            everySuspend { profileRepository.updateUserProfile() } returns
                Result.failure(Exception("Error updating profile"))

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)

            runVmTest(viewModel) {
                onAction(ProfileEditViewAction.SaveChanges)

                with(viewState.awaitContentState()) {
                    assertTrue(isSaveChangesButtonLoading)
                    assertFalse(isViewEnable)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSaveChangesButtonLoading)
                    assertTrue(isViewEnable)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )

                verifySuspend { profileRepository.updateUserProfile() }
            }
        }
}
