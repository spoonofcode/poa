package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NotificationSettingsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationSettingsViewModel
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
    fun `initial view`() = runTest {
        runVmTest(viewModel, expectMostRecentItem = false) {
            viewState.awaitInitialState()
            with(viewState.awaitContentState()) {
                assertEquals(NotificationSettings(), notificationSettings)
            }
        }
    }

    @Test
    fun `switch all notifications`() = runTest {
        runVmTest(viewModel) {
            onAction(NotificationSettingsViewAction.SwitchAllNotification(enable = true))

            with(viewState.awaitContentState()) {
                assertTrue(notificationSettings.isNotificationsPersonalizedEnabled)
                assertTrue(notificationSettings.isNotificationsFromPartnersEnabled)
                assertTrue(notificationSettings.isNotificationsMyProductSeriesEnabled)
            }
        }
    }

    @Test
    fun `save changes success`() = runTest {
        runVmTest(viewModel) {
            onAction(NotificationSettingsViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }

            verifySuspend { notificationRepository.setNotificationSettings(any(), any(), any()) }
            verifySuspend { viewModelNavigator.pop() }
        }
    }
}
