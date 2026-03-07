package com.spoonofcode.feature.product.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.launch

internal class ProductEditViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : BaseViewModel<ProductEditViewState, ProductEditViewAction>(ProductEditViewState()) {

    override fun onAction(action: ProductEditViewAction) {
        when (action) {
            is ProductEditViewAction.ChangeCustomLink -> changeCustomLink(action.link)
            is ProductEditViewAction.InitView -> initView(action.productId)
            ProductEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView(productId: String) {
        showLoadingView()
        viewModelScope.launch {
            getProductByIdUseCase(productId = productId)
                .onSuccess {
                    showContentView {
                        copy(
                            product = it
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeCustomLink(link: String) {
        val currentProduct = currentState().product
        updateState { copy(product = currentProduct?.copy(customLink = link)) }
    }

    private fun saveChanges() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentState().product)
            updateProductUseCase(
                id = product.id,
                name = product.name,
                partnerId = product.partnerId,
                description = product.description,
                imageLink = product.imageLink,
                videoLink = product.videoLink,
                seriesName = product.seriesName,
                customLink = product.customLink,
            )
                .onSuccess {
                    viewModelNavigator.pop()
                }
                .onFailure {
                    showErrorSnackbar(it)
                }
        }
    }
}