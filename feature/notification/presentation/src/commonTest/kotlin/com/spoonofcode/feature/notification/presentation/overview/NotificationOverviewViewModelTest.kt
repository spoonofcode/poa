package com.spoonofcode.feature.notification.presentation.overview

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.data.test.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.feature.notification.data.test.NotificationMockData.NOTIFICATION_1
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NotificationOverviewViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: NotificationOverviewViewModel
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationPresentationTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
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
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(NOTIFICATIONS, notifications)
                }
            }
        }

    @Test
    fun `initial view - error`() =
        runTest {
            everySuspend { notificationRepository.readAll() } returns
                Result.failure(
                    Exception("Test error"),
                )

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `select notification`() =
        runTest {
            runVmTest(viewModel) {
                onAction(NotificationOverviewViewAction.SelectNotification(notificationId = NOTIFICATION_1.id))

                verifySuspend {
                    viewModelNavigator.push(
                        NotificationModuleRoute.NotificationDetails(notificationId = NOTIFICATION_1.id),
                    )
                }
            }
        }
}
