package com.spoonofcode.feature.notification.presentation.details

import app.cash.turbine.test
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.data.mock.NotificationMockData.NOTIFICATION_1
import com.spoonofcode.feature.notification.di.notificationTestModule
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewAction
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewState
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
                expected = ViewState(
                    data = NotificationDetailsViewState(),
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(
                NotificationDetailsViewAction.InitView(
                    notificationId = NOTIFICATION_1.id,
                )
            )

            assertEquals(
                expected = ViewState(
                    data = NotificationDetailsViewState(
                        notification = NOTIFICATION_1,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }
    }
}