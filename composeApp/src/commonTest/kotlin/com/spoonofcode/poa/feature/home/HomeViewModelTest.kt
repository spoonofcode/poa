package com.spoonofcode.poa.feature.home

import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.home.di.homeTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(homeTestModule)
        super.setup()
    }

    // TODO POA-#8 - Fix and add missing Unit tests
//    @Test
//    fun `init view`() = runTest {
//        // when
//        viewModel = getSut()
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                HomeViewState(
//                    sportEventsUserParticipatedIn = SPORT_EVENTS,
//                    sportEventsCreatedByUser = SPORT_EVENTS,
//                    isLoadingView = false
//                ),
//                awaitItem(),
//            )
//        }
//    }
//
//    @Test
//    fun `init view with error`() = runTest {
//        everySuspend { sportEventRepository.readSportEventsCreatedByUser(any()) } throws Exception("test exception")
//
//        viewModel = getSut()
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                HomeViewState(
//                    isLoadingView = false,
//                    isErrorView = true,
//                ),
//                awaitItem(),
//            )
//        }
//    }
//
//    @Test
//    fun `go to create sport event`() = runTest {
//        viewModel = getSut()
//
//        viewModel.goToCreateSportEvent()
//        advanceUntilIdle()
//
//        verifySuspend { viewModelNavigator.push(ofType<SportEventEditScreen>()) }
//    }
//
//    @Test
//    fun `go to my event`() = runTest {
//        viewModel = getSut()
//        val eventId = 42
//
//        viewModel.goToMyEvent(eventId)
//        advanceUntilIdle()
//
//        verifySuspend {
//            viewModelNavigator.push(
//                eq(SportEventDetailsScreen(sportEventId = eventId))
//            )
//        }
//    }
}