package com.spoonofcode.feature.notification.presentation.settings

import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

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

    @Test
    fun `save changes error`() = runTest {
        everySuspend {
            notificationRepository.setNotificationSettings(
                any(),
                any(),
                any()
            )
        } returns Result.failure(
            Exception("Backend exception"),
        )

        val expectedMessage = UiText.Resource(CoreRes.string.default_error)

        runVmTest(viewModel) {
            onAction(NotificationSettingsViewAction.SaveChanges)

            with(viewState.awaitContentState()) {
                assertTrue(isSaveChangesButtonLoading)
            }
            with(viewState.awaitContentState()) {
                assertFalse(isSaveChangesButtonLoading)
            }
            assertEquals(
                expected = expectedMessage,
                actual = snackbarEvent.awaitItem().message,
            )

            verifySuspend { notificationRepository.setNotificationSettings(any(), any(), any()) }
        }
    }
}
