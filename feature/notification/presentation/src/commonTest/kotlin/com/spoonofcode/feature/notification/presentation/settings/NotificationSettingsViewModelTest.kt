package com.spoonofcode.feature.notification.presentation.settings

import app.cash.turbine.test
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.di.notificationTestModule
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewAction
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewState
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
                expected = ViewState(
                    data = NotificationSettingsViewState(),
                ),
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
                expected = ViewState(
                    data = NotificationSettingsViewState(),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}