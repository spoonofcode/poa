package com.spoonofcode.feature.partner.presentation.panel

import app.cash.turbine.test
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.presentation.type.NotificationTypeScreen
import com.spoonofcode.feature.partner.di.partnerTestModule
import com.spoonofcode.feature.partner.presentation.edit.PartnerEditScreen
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewAction
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewModel
import com.spoonofcode.feature.partner.presentation.panel.PartnerPanelViewState
import dev.mokkery.matcher.ofType
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
    override fun setup() {
        modules = arrayOf(partnerTestModule)
        super.setup()
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

        verifySuspend { viewModelNavigator.push(ofType<PartnerEditScreen>()) }
    }

    @Test
    fun `navigate to notification type screen`() = runTest {
        viewModel.onAction(PartnerPanelViewAction.NavigateToNotificationType)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<NotificationTypeScreen>()) }
    }
}