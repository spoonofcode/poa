package com.spoonofcode.feature.notification.presentation.details

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.feature.notification.data.test.NotificationMockData.NOTIFICATION_1
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationDetailsViewModel

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationPresentationTestModule)
        super.beforeTest()
        viewModel = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
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