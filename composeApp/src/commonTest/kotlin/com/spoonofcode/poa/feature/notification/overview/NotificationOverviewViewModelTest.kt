package com.spoonofcode.poa.feature.notification.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationOverviewViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationOverviewViewModel
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(notificationTestModule)
        super.setup()
        notificationRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = NotificationOverviewViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationOverviewViewAction.InitView)

            assertEquals(
                expected = NotificationOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    notifications = NOTIFICATIONS,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            notificationRepository.readAll()
        }
    }
}