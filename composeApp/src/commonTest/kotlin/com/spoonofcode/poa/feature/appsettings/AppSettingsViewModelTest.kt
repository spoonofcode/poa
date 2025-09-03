package com.spoonofcode.poa.feature.appsettings

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.appsettings.di.appSettingsTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AppSettingsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: AppSettingsViewModel
    private lateinit var roleRepository: RoleRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(appSettingsTestModule)
        super.setup()
        roleRepository = getKoin().get()
    }

    @Test
    fun `init view when user HAS special settings`() = runTest {
        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                AppSettingsViewState(
                    isLoadingView = false,
//                    isSearchUserButtonVisible = true,
                ),
                awaitItem(),
            )
        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
//    @Test
//    fun `init view when user does NOT have special settings`() = runTest {
//        everySuspend { roleRepository.readAllRolesByUserId(any()) } returns listOf(ROLE_4)
//
//        viewModel = getSut()
//        viewModel.initView()
//        advanceUntilIdle()
//
//        viewModel.viewState.test {
//            assertEquals(
//                AppSettingsViewState(
//                    isLoadingView = false,
//                    isSearchUserButtonVisible = false,
//                ),
//                awaitItem(),
//            )
//        }
//    }

    @Test
    fun `init view with error`() = runTest {
        everySuspend { roleRepository.readAllRolesByUserId(any()) } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                AppSettingsViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    // TODO POA-#8 - Fix and add missing Unit tests
//    @Test
//    fun `navigate to search user`() = runTest {
//        viewModel = getSut()
//
//        viewModel.navigateToSearchUser()
//        advanceUntilIdle()
//
//        verifySuspend { viewModelNavigator.push(ofType<SearchUserScreen>()) }
//    }
}