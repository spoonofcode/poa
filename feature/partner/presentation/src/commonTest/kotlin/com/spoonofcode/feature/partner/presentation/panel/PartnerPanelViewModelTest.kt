package com.spoonofcode.feature.partner.presentation.panel

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class PartnerPanelViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: PartnerPanelViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view`() =
        runTest {
            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitContentState()
            }
        }

    @Test
    fun `navigate to partner edit screen`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerPanelViewAction.NavigateToPartnerEdit)

                verifySuspend { viewModelNavigator.push(PartnerModuleRoute.PartnerEdit) }
            }
        }

    @Test
    fun `navigate to notification type screen`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerPanelViewAction.NavigateToNotificationType)

                verifySuspend { viewModelNavigator.push(PartnerModuleRoute.PartnerNotificationType) }
            }
        }

    @Test
    fun `navigate to partner notifications screen`() =
        runTest {
            runVmTest(viewModel) {
                onAction(PartnerPanelViewAction.NavigateToPartnerNotifications)

                verifySuspend { viewModelNavigator.push(PartnerModuleRoute.PartnerNotifications) }
            }
        }
}
