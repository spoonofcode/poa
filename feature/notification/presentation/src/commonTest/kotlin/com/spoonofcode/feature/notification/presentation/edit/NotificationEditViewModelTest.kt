package com.spoonofcode.feature.notification.presentation.edit

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.core.test.base.BaseViewModelTest
import com.spoonofcode.poa.core.ext.containsTypes
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.di.notificationTestModule
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewModel
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        viewModel = getSut()
    }

    @Test
    fun `initial state is default`() = runTest {
        viewModel.viewState.test {
            assertEquals(
                expected = ViewState(
                    data = NotificationEditViewState(),
                    screenState = ScreenState.CONTENT,
                ),
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
                expected = ViewState(
                    data = NotificationEditViewState(
                        itemIds = itemIds,
                    ),
                    screenState = ScreenState.CONTENT,
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
                expected = ViewState(
                    data = NotificationEditViewState(
                        link = link,
                    ),
                    screenState = ScreenState.CONTENT,
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
                expected = ViewState(
                    data = NotificationEditViewState(
                        text = text,
                    ),
                    screenState = ScreenState.CONTENT,
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
                expected = ViewState(
                    data = NotificationEditViewState(
                        title = title,
                    ),
                    screenState = ScreenState.CONTENT,
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
                expected = ViewState(
                    data = NotificationEditViewState(
                        itemIds = itemIds,
                        title = title,
                        text = text,
                        link = link,
                        expirationDateTime = expirationDateTime
                    ),
                    screenState = ScreenState.LOADING,
                ),
                actual = awaitItem()
            )
        }

        verifySuspend {
            notificationRepository.send(any(), any(), any(), any(), any(), any(), any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}