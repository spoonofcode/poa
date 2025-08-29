package com.spoonofcode.poa.feature.notification.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.network.getUrlOpener
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Countdown
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.open_link
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationDetailsScreen(
    private val notificationId: Int,
) : BaseScreen<NotificationDetailsViewModel, NotificationDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(viewModel: NotificationDetailsViewModel?): () -> Unit =
        { viewModel?.initView(notificationId = notificationId) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationDetailsViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationDetailsViewModel?,
        viewState: NotificationDetailsViewState,
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
    )

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

            val notification = requireNotNull(viewState.notification)

            Texts.BL(notification.title)

            Spacers.VerticalBetweenFields()

            Texts.BL(notification.text)

            if (notification.expirationDate !== null) {
                Spacers.VerticalBetweenFields()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Countdown(notification.expirationDate)
                }
            }

            if (notification.link.isNullOrEmpty().not()) {
                val urlOpener = remember { getUrlOpener() }
                Spacers.VerticalBetweenFields()
                Buttons.PrimaryButton(
                    text = stringResource(Res.string.open_link),
                    onClick = { urlOpener.openUrl(notification.link) }
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationDetailsScreenContentPreview() {
    NotificationDetailsScreen(notificationId = 1).PreviewContent(
        NotificationDetailsViewState()
    )
}
// endregion