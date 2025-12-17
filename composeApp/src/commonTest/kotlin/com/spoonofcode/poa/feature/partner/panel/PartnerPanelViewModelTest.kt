package com.spoonofcode.poa.feature.partner.panel

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.feature.notification.type.NotificationTypeScreen
import com.spoonofcode.poa.feature.partner.di.partnerTestModule
import com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditScreen
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewModel

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
                expected = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `navigate to partner edit screen`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction.NavigateToPartnerEdit)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<com.spoonofcode.poa.feature.partner.presentation.edit.PartnerEditScreen>()) }
    }

    @Test
    fun `navigate to notification type screen`() = runTest {
        viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction.NavigateToNotificationType)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<NotificationTypeScreen>()) }
    }
}