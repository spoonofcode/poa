package com.spoonofcode.feature.partner.presentation.notification.edit

import com.spoonofcode.core.data.ext.plus
import com.spoonofcode.core.data.utils.LocalDateUtils
import com.spoonofcode.core.presentation.default_error
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.awaitContentState
import com.spoonofcode.core.presentation.test.ext.awaitErrorState
import com.spoonofcode.core.presentation.test.ext.awaitInitialState
import com.spoonofcode.core.presentation.test.ext.runVmTest
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import com.spoonofcode.feature.partner.presentation.di.partnerPresentationTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus
import org.koin.core.parameter.parametersOf
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.spoonofcode.core.presentation.Res as CoreRes

class PartnerNotificationEditViewModelTest : BaseViewModelTest() {
    private lateinit var notificationRepository: PartnerNotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerPresentationTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `initial view create new notification`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(PartnerNotificationType.GLOBAL, notificationType)
                    assertEquals(setOf("global"), selectedTopics)
                    assertEquals(setOf("Global"), selectedNames)
                }
            }
        }

    @Test
    fun `initial view edit existing notification`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = "1",
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals("1", notificationId)
                    assertEquals("Title", title)
                    assertEquals("Text", text)
                }
            }
        }

    @Test
    fun `initial view edit existing notification - error`() =
        runTest {
            val notificationId = "1"
            everySuspend { notificationRepository.getNotification(notificationId) } returns
                Result.failure(
                    Exception("Test error"),
                )

            val args =
                PartnerNotificationEditArgs(
                    notificationId = notificationId,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                viewState.awaitErrorState()
            }
        }

    @Test
    fun `change title text and link`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ChangeTitle("New Title"))
                with(viewState.awaitContentState()) {
                    assertEquals("New Title", title)
                }

                onAction(PartnerNotificationEditViewAction.ChangeText("New Text"))
                with(viewState.awaitContentState()) {
                    assertEquals("New Text", text)
                }

                onAction(PartnerNotificationEditViewAction.ChangeLink("https://example.com"))
                with(viewState.awaitContentState()) {
                    assertEquals("https://example.com", link)
                    assertTrue(isLinkValid)
                    assertTrue(isSendNotificationEnabled)
                }
            }
        }

    @Test
    fun `change link to invalid disables send notification and sets link invalid`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ChangeTitle("New Title"))
                viewState.awaitContentState()

                onAction(PartnerNotificationEditViewAction.ChangeText("New Text"))
                with(viewState.awaitContentState()) {
                    assertTrue(isLinkValid)
                    assertTrue(isSendNotificationEnabled)
                }

                onAction(PartnerNotificationEditViewAction.ChangeLink("http://example.com"))
                with(viewState.awaitContentState()) {
                    assertEquals("http://example.com", link)
                    assertFalse(isLinkValid)
                    assertFalse(isSendNotificationEnabled)
                }

                onAction(PartnerNotificationEditViewAction.ChangeLink(""))
                with(viewState.awaitContentState()) {
                    assertEquals("", link)
                    assertTrue(isLinkValid)
                    assertTrue(isSendNotificationEnabled)
                }
            }
        }

    @Test
    fun `max delivery date is limited to 29 days from today`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel, expectMostRecentItem = false) {
                viewState.awaitInitialState()
                with(viewState.awaitContentState()) {
                    assertEquals(
                        LocalDateUtils.today().plus(29, DateTimeUnit.DAY),
                        maxDeliveryDateTime.date,
                    )
                }
            }
        }

    @Test
    fun `change delivery and expiration date time`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            val newDelivery = LocalDateTime(2025, 1, 1, 12, 0)
            val newExpiration = LocalDateTime(2025, 1, 2, 12, 0)

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ChangeDeliveryDateTime(newDelivery))
                with(viewState.awaitContentState()) {
                    assertEquals(newDelivery, deliveryDateTime)
                }

                onAction(PartnerNotificationEditViewAction.ChangeExpirationDateTime(newExpiration))
                with(viewState.awaitContentState()) {
                    assertEquals(newExpiration, expirationDateTime)
                }
            }
        }

    @Test
    fun `send notification dialog visibility`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.SendNotification)
                with(viewState.awaitContentState()) {
                    assertTrue(isSendNotificationDialogVisible)
                }

                onAction(PartnerNotificationEditViewAction.CancelSendNotification)
                with(viewState.awaitContentState()) {
                    assertFalse(isSendNotificationDialogVisible)
                }
            }
        }

    @Test
    fun `confirm send new notification success`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ConfirmSendNotification)

                with(viewState.awaitContentState()) {
                    assertTrue(isSendNotificationButtonLoading)
                    assertFalse(isSendNotificationDialogVisible)
                }

                verifySuspend {
                    notificationRepository.createNotification(
                        title = any(),
                        text = any(),
                        link = any(),
                        topics = any(),
                        deliveryDateTime = any(),
                        expirationDateTime = any(),
                        ownerId = any(),
                    )
                }
                verifySuspend {
                    viewModelNavigator.replaceAll(
                        listOf(
                            ProfileModuleRoute.ProfileDetails,
                            PartnerModuleRoute.PartnerPanel,
                            PartnerModuleRoute.PartnerNotifications,
                        ),
                    )
                }
            }
        }

    @Test
    fun `confirm send new notification error`() =
        runTest {
            everySuspend {
                notificationRepository.createNotification(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                )
            } returns Result.failure(Exception("Error creating notification"))

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)
            val args =
                PartnerNotificationEditArgs(
                    notificationId = null,
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ConfirmSendNotification)

                with(viewState.awaitContentState()) {
                    assertTrue(isSendNotificationButtonLoading)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSendNotificationButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )
            }
        }

    @Test
    fun `confirm update notification success`() =
        runTest {
            val args =
                PartnerNotificationEditArgs(
                    notificationId = "1",
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ConfirmSendNotification)

                with(viewState.awaitContentState()) {
                    assertTrue(isSendNotificationButtonLoading)
                    assertFalse(isSendNotificationDialogVisible)
                }

                verifySuspend {
                    notificationRepository.updateNotification(
                        notificationId = any(),
                        title = any(),
                        text = any(),
                        link = any(),
                        topics = any(),
                        deliveryDateTime = any(),
                        expirationDateTime = any(),
                        ownerId = any(),
                    )
                }
                verifySuspend { viewModelNavigator.pop() }
            }
        }

    @Test
    fun `confirm update notification error`() =
        runTest {
            everySuspend {
                notificationRepository.updateNotification(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                )
            } returns Result.failure(Exception("Error updating notification"))

            val expectedErrorMessage = UiText.Resource(CoreRes.string.default_error)
            val args =
                PartnerNotificationEditArgs(
                    notificationId = "1",
                    selectedTopics = setOf("global"),
                    selectedNames = setOf("Global"),
                )
            val viewModel: PartnerNotificationEditViewModel = getKoin().get { parametersOf(args) }

            runVmTest(viewModel) {
                onAction(PartnerNotificationEditViewAction.ConfirmSendNotification)

                with(viewState.awaitContentState()) {
                    assertTrue(isSendNotificationButtonLoading)
                }
                with(viewState.awaitContentState()) {
                    assertFalse(isSendNotificationButtonLoading)
                }
                assertEquals(
                    expected = expectedErrorMessage,
                    actual = snackbarEvent.awaitItem().message,
                )
            }
        }
}
