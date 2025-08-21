package com.spoonofcode.poa.feature.notification.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.messagefcm.MessageFCMScreen
import com.spoonofcode.poa.feature.messagefcm.MessageFCMViewState
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationDetailsScreen(
    private val notificationId: Int,
) : BaseScreen<NotificationDetailsViewModel, NotificationDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideReloadAction(viewModel: NotificationDetailsViewModel): () -> Unit =
        { viewModel.initView(notificationId = notificationId) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationDetailsViewModel?,
        viewState: NotificationDetailsViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel?.initView(notificationId = notificationId)
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: NotificationDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
//            CoilImage(
//                imageModel = { viewState.notification.imageLink }, // loading a network image or local resource using an URL.
//                imageOptions = ImageOptions(
//                    contentScale = ContentScale.Crop,
//                    alignment = Alignment.Center
//                )
//            )
            Spacers.VerticalBetweenFields()
            Texts.BM(viewState.notification!!.title)
            Spacers.VerticalBetweenFields()
            Texts.BM(viewState.notification.text)

            if (viewState.notification.link != null) {
                Spacers.VerticalBetweenFields()
                Texts.BM(viewState.notification.link)
            }

            if (viewState.notification.expirationDate != null) {
                Spacers.VerticalBetweenFields()
                Texts.BM(viewState.notification.expirationDate.toString())
            }
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedNotificationDetailsScreenPreview() {
        NotificationDetailsScreen(notificationId = 1).PreviewContent(
            viewState = NotificationDetailsViewState(),
        )
    }
    // endregion
}