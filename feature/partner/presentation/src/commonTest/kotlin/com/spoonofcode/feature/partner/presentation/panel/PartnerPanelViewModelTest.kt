package com.spoonofcode.feature.partner.presentation.panel

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PartnerPanelViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PartnerPanelViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = PartnerPanelViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigate to partner edit screen`() = runTest {
        viewModel.onAction(PartnerPanelViewAction.NavigateToPartnerEdit)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(PartnerModuleRoute.PartnerEdit(partnerId = any())) }
        verifySuspend { viewModelNavigator.push(any()) }

    }

    @Test
    fun `navigate to notification type screen`() = runTest {
        viewModel.onAction(PartnerPanelViewAction.NavigateToNotificationType)
        advanceUntilIdle()

        verifySuspend { routeResolver.resolve(NotificationModuleRoute.NotificationType) }
        verifySuspend { viewModelNavigator.push(any()) }
    }
}