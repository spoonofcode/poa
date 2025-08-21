package com.spoonofcode.poa.feature.partner.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partner
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerEditScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<PartnerEditViewModel, PartnerEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner

    override fun provideReloadAction(viewModel: PartnerEditViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerEditViewModel?,
        viewState: PartnerEditViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerEditViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB("HOME")

            //            TextFields.Outlined(
//                value = viewState.title,
//                onValueChange = { changeTitle(it) },
//                label = stringResource(resource = Res.string.title),
//            )
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedHomeScreenPreview() {
        ContentView(
            viewState = PartnerEditViewState(),
        )
    }
    // endregion
}