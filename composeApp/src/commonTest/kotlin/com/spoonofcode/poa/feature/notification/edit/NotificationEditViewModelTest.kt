package com.spoonofcode.poa.feature.notification.edit

import app.cash.turbine.test
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.BaseViewModelTest
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

    private lateinit var viewModel: com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewModel
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
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(),
                actual = awaitItem()
            )
        }
    }

    @Test
    fun `init view`() = runTest {
        val itemIds = listOf("1", "2")
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.InitView(itemIds = itemIds))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeLink(link))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeText(text))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeTitle(title))

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
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

            viewModel.onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.SendNotification)

            assertEquals(
                expected = _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState(
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
            notificationDataSource.create(any(), any(), any(), any(), any(), any(), any())
        }
        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(MainHostScreen::class))
        }
    }
}