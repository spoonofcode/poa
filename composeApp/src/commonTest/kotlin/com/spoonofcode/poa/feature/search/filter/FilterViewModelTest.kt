package com.spoonofcode.poa.feature.search.filter

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterData
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import com.spoonofcode.poa.feature.search.di.searchTestModule
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FilterViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: FilterViewModel

    private lateinit var NFTInfoFilterRepository: NFTInfoFilterRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(searchTestModule)
        super.setup()
        NFTInfoFilterRepository = getKoin().get()
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `init view with NO previous selections`() = runTest {
        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
//            selectedClubId = ALL_OPTION_ID,
//            selectedCoachId = ALL_OPTION_ID,
//            selectedLevelId = ALL_OPTION_ID,
//            selectedTypeId = ALL_OPTION_ID,
//            clubs = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(CLUBS.associate { it.id to it.name }),
//            coaches = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(USERS.associate { it.id to it.fullName }),
//            levels = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(LEVELS.associate { it.id to it.name }),
//            types = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(TYPES.associate { it.id to it.name }),
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `init view with PREVIOUS selections`() = runTest {
        val startDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
        val endDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)

        every { NFTInfoFilterRepository.getSelectedFilters() } returns NFTInfoFilterData(
//            selectedClubId = CLUB_1.id,
//            selectedCoachId = USER_1.id,
//            selectedLevelId = LEVEL_1.id,
//            selectedTypeId = TYPE_1.id,
//            startDateTime = startDateTime,
//            endDateTime = endDateTime,
        )

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
//            selectedClubId = CLUB_1.id,
//            selectedCoachId = USER_1.id,
//            selectedLevelId = LEVEL_1.id,
//            selectedTypeId = TYPE_1.id,
//            clubs = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(CLUBS.associate { it.id to it.name }),
//            coaches = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(USERS.associate { it.id to it.fullName }),
//            levels = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(LEVELS.associate { it.id to it.name }),
//            types = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(TYPES.associate { it.id to it.name }),
            startDateTime = startDateTime,
            endDateTime = endDateTime,
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `init view with ERROR`() = runTest {
//        everySuspend { clubRepository.readAll() } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
            isErrorView = true,
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `change club updates state`() = runTest {
//        viewModel = getSut()
//        advanceUntilIdle()
//
//        val newClubId = CLUB_1.id
//        viewModel.changeClub(newClubId)
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(newClubId, awaitItem().selectedClubId)
//        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `apply filter SUCCESS`() = runTest {
        viewModel = getSut()
        advanceUntilIdle()

//        viewModel.changeClub(CLUB_1.id)
//        viewModel.changeCoach(USER_1.id)
//        viewModel.changeLevel(LEVEL_1.id)
//        viewModel.changeType(TYPE_1.id)
//        val startDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
//        val endDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)
//        viewModel.changeStartDateTime(startDateTime)
//        viewModel.changeEndDateTime(endDateTime)
        advanceUntilIdle()

        viewModel.applyFilter()
        advanceUntilIdle()

        verifySuspend {
            NFTInfoFilterRepository.setSelectedFilters(
                newNFTInfoFilterData = NFTInfoFilterData(
//                    selectedClubId = CLUB_1.id,
//                    selectedCoachId = USER_1.id,
//                    selectedLevelId = LEVEL_1.id,
//                    selectedTypeId = TYPE_1.id,
//                    startDateTime = startDateTime,
//                    endDateTime = endDateTime,
                )
            )
        }
        verifySuspend { viewModelNavigator.pop() }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
    @Ignore
    @Test
    fun `apply filter ERROR`() = runTest {
        every { NFTInfoFilterRepository.setSelectedFilters(any()) } throws Exception("test exception")

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.applyFilter()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }
}