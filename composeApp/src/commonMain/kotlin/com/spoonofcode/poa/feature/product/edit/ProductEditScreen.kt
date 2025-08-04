package com.spoonofcode.poa.feature.product.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.product
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProductEditScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProductEditViewModel, ProductEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.product

    override fun provideReloadAction(viewModel: ProductEditViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductEditViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ProductEditViewModel,
        viewState: ProductEditViewState
    ): @Composable ColumnScope.() -> Unit {

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: ProductEditViewState,
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
            viewState = ProductEditViewState(),
        )
    }
    // endregion
}