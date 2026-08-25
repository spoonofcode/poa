package com.spoonofcode.feature.product.presentation.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.launch

internal class ProductEditViewModel(
    private val productId: String,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : BaseViewModel<ProductEditViewState, ProductEditViewAction, Nothing>() {
    override val startAction = {
        initView(productId)
    }

    override fun onAction(action: ProductEditViewAction) {
        when (action) {
            is ProductEditViewAction.ChangeCustomLink -> changeCustomLink(action.link)
            ProductEditViewAction.SaveChanges -> saveChanges()
        }
    }

    private fun initView(productId: String) {
        showLoadingView()
        viewModelScope.launch {
            getProductByIdUseCase(productId = productId)
                .onSuccess {
                    setContentState(
                        ProductEditViewState(
                            product = it
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun changeCustomLink(link: String) {
        val currentProduct = currentContentState().product
        updateContentState { copy(product = currentProduct.copy(customLink = link)) }
    }

    private fun saveChanges() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentContentState().product)
            updateProductUseCase(
                id = product.id,
                customLink = product.customLink,
            )
                .onSuccess {
                    viewModelNavigator.pop()
                }
                .onFailure {
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }
}