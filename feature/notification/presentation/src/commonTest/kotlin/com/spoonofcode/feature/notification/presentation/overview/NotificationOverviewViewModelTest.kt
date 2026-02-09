package com.spoonofcode.feature.notification.presentation.overview

import app.cash.turbine.test
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.di.notificationTestModule
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewModel
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewState
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
                expected = ViewState(
                    data = NotificationOverviewViewState(),
                ),
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
                expected = ViewState(
                    data = NotificationOverviewViewState(
                        notifications = NOTIFICATIONS,
                    ),
                    screenState = ScreenState.CONTENT,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            notificationRepository.readAll()
        }
    }
}