package com.spoonofcode.poa.feature.notification.details

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATION_1
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewModel

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
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewAction.InitView(
                    notificationId = NOTIFICATION_1.id,
                )
            )

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState(
                    screenState = ScreenState.CONTENT,
                    notification = NOTIFICATION_1,
                ),
                actual = awaitItem()
            )
        }
    }
}