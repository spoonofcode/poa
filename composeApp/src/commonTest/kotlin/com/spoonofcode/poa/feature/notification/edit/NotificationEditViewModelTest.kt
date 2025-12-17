package com.spoonofcode.poa.feature.notification.edit

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.di.notificationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationEditViewModel
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
                expected = NotificationEditViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        val itemIds = listOf("1", "2")
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.InitView(itemIds = itemIds))

            assertEquals(
                expected = NotificationEditViewState(
                    itemIds = itemIds,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change link`() = runTest {
        val link = "https://example.com"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.ChangeLink(link))

            assertEquals(
                expected = NotificationEditViewState(
                    link = link,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change text`() = runTest {
        val text = "New text"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.ChangeText(text))

            assertEquals(
                expected = NotificationEditViewState(
                    text = text,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `change title`() = runTest {
        val title = "New title"
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.ChangeTitle(title))

            assertEquals(
                expected = NotificationEditViewState(
                    title = title,
                ),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `send notification success`() = runTest {
        val itemIds = listOf("1", "2")
        val title = "New title"
        val text = "New text"
        val link = "New link"
        val expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1)
        viewModel.updateState {
            copy(
                itemIds = itemIds,
                title = title,
                text = text,
                link = link,
                expirationDateTime = expirationDateTime
            )
        }

        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.SendNotification)

            assertEquals(
                expected = NotificationEditViewState(
                    screenState = ScreenState.LOADING,
                    itemIds = itemIds,
                    title = title,
                    text = text,
                    link = link,
                    expirationDateTime = expirationDateTime
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            notificationRepository.create(any(), any(), any(), any(), any(), any(), any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}