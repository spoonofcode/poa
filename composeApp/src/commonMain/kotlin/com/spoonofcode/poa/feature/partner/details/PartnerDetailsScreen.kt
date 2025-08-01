package com.spoonofcode.poa.feature.partner.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.home
import org.jetbrains.compose.resources.StringResource

internal class PartnerDetailsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.home,
    override val backNavigationEnable: Boolean = false,
    override val contentPadding: PaddingValues = PaddingValues(
        start = Paddings.screenPadding,
        top = Paddings.screenPadding,
        bottom = Paddings.screenPadding,
    )
) : BaseScreen<PartnerDetailsViewModel, PartnerDetailsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: PartnerDetailsViewModel,
        viewState: PartnerDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerDetailsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB("HOME")
        }
    }

    // region previews
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedHomeScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = PartnerDetailsViewState(),
            )
        )
    }
    // endregion
}