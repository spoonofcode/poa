package com.spoonofcode.feature.notification.presentation.edit

import app.cash.turbine.test
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.test.base.BaseViewModelTest
import com.spoonofcode.core.presentation.test.ext.containsTypes
import com.spoonofcode.feature.appnavigation.AppModuleRoute
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.notification.presentation.di.notificationPresentationTestModule
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NotificationEditViewModel
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
        val selectedTopics = setOf("test1@gmail.com", "test2@gmail.com")
        val selectedNames = setOf("Test name 1", "Test name 2")
        viewModel.viewState.test {
            skipItems(1)

            viewModel.onAction(NotificationEditViewAction.InitView(
                selectedTopics = selectedTopics,
                selectedNames = selectedNames,
            ))

            assertEquals(
                expected = ViewState(
                    data = NotificationEditViewState(
                        selectedTopics = selectedTopics,
                        selectedNames = selectedNames,
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
        val selectedTopics = setOf("test1@gmail.com", "test2@gmail.com")
        val selectedNames = setOf("Test name 1", "Test name 2")
        val title = "New title"
        val text = "New text"
        val link = "New link"
        val expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1)
        viewModel.updateState {
            copy(
                selectedTopics = selectedTopics,
                selectedNames = selectedNames,
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
                        selectedTopics = selectedTopics,
                        selectedNames = selectedNames,
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
            notificationRepository.create(any(), any(), any(), any(), any(), any())
        }

        verifySuspend { routeResolver.resolve(AppModuleRoute.MainHost) }
        verifySuspend { viewModelNavigator.replaceAll(any()) }

        verifySuspend {
            viewModelNavigator.replaceAll(containsTypes(AppModuleRoute.MainHost::class))
        }
    }
}