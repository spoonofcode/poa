package com.spoonofcode.poa.feature.search.search

import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import com.spoonofcode.poa.feature.search.SearchViewModel
import com.spoonofcode.poa.feature.search.di.searchTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var NFTInfoFilterRepository: NFTInfoFilterRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(searchTestModule)            // Koin test module with mocks
        super.setup()
        NFTInfoFilterRepository = getKoin().get()
    }
    // TODO POA-#8 - Fix and add missing Unit tests

//    @Test
//    fun `init view success`() = runTest {
//        every { filterRepository.getSelectedFilters() } returns FilterData(
//            startDateTime = LocalDateTime(2022, 1, 1, 1, 1),
//            endDateTime = LocalDateTime(2025, 1, 1, 1, 1),
//        )
//
//        viewModel = getSut()
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                SearchViewState(
//                    isLoadingView = false,
//                    initSportEvents = SPORT_EVENTS,
//                    filteredSportEvents = SPORT_EVENTS,
//                ),
//                awaitItem(),
//            )
//        }
//    }
//
//    @Test
//    fun `init view error`() = runTest {
//        everySuspend { sportEventRepository.readAll() } throws Exception("load failed")
//
//        viewModel = getSut()
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                SearchViewState(
//                    isLoadingView = false,
//                    isErrorView = true,
//                ),
//                awaitItem(),
//            )
//        }
//    }
//
//    @Test
//    fun `change search text filters list and updates state`() = runTest {
//        every { filterRepository.getSelectedFilters() } returns FilterData(
//            startDateTime = LocalDateTime(2022, 1, 1, 1, 1),
//            endDateTime = LocalDateTime(2025, 1, 1, 1, 1),
//        )
//
//        val query = SPORT_EVENT_1.title
//
//        viewModel = getSut()
//        advanceUntilIdle()
//
//        viewModel.changeSearchText(query)
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                listOf(SPORT_EVENT_1),
//                awaitItem().filteredSportEvents,
//            )
//        }
//    }
//
//    @Test
//    fun `select sport event navigates to details`() = runTest {
//        viewModel = getSut()
//        val id = 42
//
//        viewModel.selectSportEvent(id)
//        advanceUntilIdle()
//
//        verifySuspend {
//            viewModelNavigator.push(eq(SportEventDetailsScreen(sportEventId = id)))
//        }
//    }
//
//    @Test
//    fun `navigate to filter`() = runTest {
//        viewModel = getSut()
//
//        viewModel.navigateToFilter()
//        advanceUntilIdle()
//
//        verifySuspend { viewModelNavigator.push(ofType<FilterScreen>()) }
//    }
}