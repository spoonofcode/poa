package com.spoonofcode.poa.feature.profile

import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.feature.profile.details.ProfileDetailsViewModel
import com.spoonofcode.poa.feature.profile.di.profileTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileDetailsViewModel
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
        )
        super.setup()
        profileRepository = getKoin().get()
    }

//    @Test
//    fun `init view`() = runTest {
//        viewModel = getSut()
//
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                ProfileViewState(
//                    profile = Profile(
//                        name = "Profile name 1",
//                        numberOfEventsUserParticipatedIn = 1,
//                        numberOfEventsCreatedByUser = 1
//                    ),
//                    isLoadingView = false
//                ),
//                awaitItem()
//            )
//        }
//    }
//
//    @Test
//    fun `init view with error`() = runTest {
//        everySuspend { profileRepository.read(any()) } throws Exception("test exception")
//
//        viewModel = getSut()
//
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                ProfileViewState(
//                    profile = null,
//                    isLoadingView = false,
//                    isErrorView = true,
//                ),
//                awaitItem()
//            )
//        }
//    }
//
//    @Test
//    fun `navigate to settings`() = runTest {
//        viewModel = getSut()
//
//        viewModel.navigateToSettings()
//        advanceUntilIdle()
//
//        verifySuspend { viewModelNavigator.push(ofType<AppSettingsScreen>()) }
//    }
}