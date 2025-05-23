package com.spoonofcode.poa.feature.profile

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.feature.profile.di.profileTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
        )
        super.setup()
    }

//    @Test
//    fun testInitial() = runTest {
//        viewModel = getSut()
//
//        viewModel.testBartek()
//
//        viewModel.viewState.test {
//            assertEquals(ProfileViewState(), awaitItem())
//        }
//    }

    @Test
    fun testInitial() = runTest {
        viewModel = getSut()

        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                ProfileViewState(
                    profile = Profile(
                        name = "Profile name 1",
                        numberOfEventsUserParticipatedIn = 1,
                        numberOfEventsCreatedByUser = 1
                    ),
                    isLoadingView = false
                ),
                awaitItem()
            )
        }
    }
}