package com.spoonofcode.poa.feature.notification.overview

import app.cash.turbine.test
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewModel
    private lateinit var notificationDataSource: NotificationDataSource

    @BeforeTest
    override fun setup() {
        modules = arrayOf(notificationTestModule)
        super.setup()
        notificationDataSource = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction.InitView)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState(
                    screenState = ScreenState.CONTENT,
                    notifications = NOTIFICATIONS,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            notificationDataSource.readAll()
        }
    }
}