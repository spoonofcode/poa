package com.spoonofcode.poa.feature.notification.settings

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationSettingsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationSettingsViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(notificationTestModule)
        super.setup()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = NotificationSettingsViewState(),
                actual = awaitItem()
            )
        }
    }

    // TODO Fix this test
    @Ignore
    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationSettingsViewAction.InitView)

            assertEquals(
                expected = NotificationSettingsViewState(
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}