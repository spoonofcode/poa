package com.spoonofcode.poa.feature.profile

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.ProfileRequest
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.profile.di.profileTestModule
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.koin.dsl.module
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest : BaseViewModelTest() {

    private fun profileRepositoryMock() = mock<ProfileRepository>()
        .apply {
            runBlocking {
                everySuspend { create(any<ProfileRequest>()) } returns Profile(
                    name = "dsa",
                    numberOfEventsUserParticipatedIn = 1,
                    numberOfEventsCreatedByUser = 1
                )
            }
        }
        .apply {
            runBlocking {
                everySuspend { read(any()) } returns Profile(
                    name = "Profile name 1",
                    numberOfEventsUserParticipatedIn = 1,
                    numberOfEventsCreatedByUser = 1
                )
            }
        }

    private fun sessionManagerMock() = mock<SessionManager>()
        .apply {
            every { getSessionUserId() } returns 1
        }

//    private val networkManagerMock = mock<NetworkManager>()
//        .apply {
//            every { observeNetworkState() } returns MutableSharedFlow(1)
//        }


    private lateinit var viewModel: ProfileViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
            module {
                single { profileRepositoryMock() }
                single { sessionManagerMock() }
//                single { networkManagerMock }
            },
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