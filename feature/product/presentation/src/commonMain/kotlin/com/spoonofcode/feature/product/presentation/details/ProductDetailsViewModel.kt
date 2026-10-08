package com.spoonofcode.feature.product.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.domain.usecase.AddProductToUserUseCase
import com.spoonofcode.feature.product.domain.usecase.DeleteProductFromUserUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductByIdUseCase
import com.spoonofcode.feature.product.domain.usecase.GetProductStatusUseCase
import com.spoonofcode.feature.product.presentation.ProductEvent
import com.spoonofcode.feature.product.presentation.ProductEventBus
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.product_updated
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

internal class ProductDetailsViewModel(
    private val productId: String,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToUserUseCase: AddProductToUserUseCase,
    private val deleteProductFromUserUseCase: DeleteProductFromUserUseCase,
    private val getProductStatusUseCase: GetProductStatusUseCase,
    private val productEventBus: ProductEventBus,
) : BaseViewModel<ProductDetailsViewState, ProductDetailsViewAction, Nothing>() {
    init {
        observeProductEvents()
    }

    override val startAction = {
        initView(productId = productId)
    }

    override fun onAction(action: ProductDetailsViewAction) {
        when (action) {
            ProductDetailsViewAction.AddProductToProfile -> addProductToProfile()
            ProductDetailsViewAction.DeleteProductFromProfile -> deleteProductFromProfile()
            ProductDetailsViewAction.EditProduct -> editProduct()
            ProductDetailsViewAction.CancelDeleteProductFromProfile -> cancelDeleteProductFromProfile()
            ProductDetailsViewAction.ConfirmDeleteProductFromProfile -> confirmDeleteProductFromProfile()
        }
    }

    private fun observeProductEvents() {
        viewModelScope.launch {
            productEventBus.detailsEvents.collect { event ->
                if (event is ProductEvent.ProductUpdated) {
                    initView(productId)
                    showSuccessSnackbar(getString(Res.string.product_updated))
                }
            }
        }
    }

    private fun initView(productId: String) {
        showLoadingView()
        viewModelScope.launch {
            getProductByIdUseCase(productId = productId)
                .onSuccess { product ->
                    val productStatus = getProductStatusUseCase(ownerEmail = product.ownerEmail)
                    setContentState(
                        ProductDetailsViewState(
                            product = product,
                            productStatus = productStatus,
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun addProductToProfile() {
        updateContentState { copy(isAddProductToProfileButtonLoading = true) }
        viewModelScope.launch {
            val product = requireNotNull(currentContentState().product)
            addProductToUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            ).onSuccess {
                productEventBus.emitOverview(ProductEvent.ProductAdded)
                viewModelNavigator.replaceAll(
                    listOf(ProductModuleRoute.ProductOverview)
                )
            }.onFailure {
                updateContentState { copy(isAddProductToProfileButtonLoading = false) }
                it.message?.let { message -> showErrorSnackbar(message) }
            }
        }
    }

    private fun editProduct() {
        val productId = requireNotNull(currentContentState().product).id
        viewModelScope.launch {
            viewModelNavigator.push(ProductModuleRoute.ProductEdit(productId = productId))
        }
    }

    private fun deleteProductFromProfile() {
        updateContentState {
            copy(
                isDeleteProductDialogVisible = true,
            )
        }
    }

    private fun cancelDeleteProductFromProfile() {
        updateContentState {
            copy(
                isDeleteProductDialogVisible = false,
            )
        }
    }

    private fun confirmDeleteProductFromProfile() {
        showLoadingView()
        viewModelScope.launch {
            val product = requireNotNull(currentContentState().product)
            deleteProductFromUserUseCase(
                productId = product.id,
                productSerieId = product.seriesId,
            )
                .onSuccess {
                    productEventBus.emitOverview(ProductEvent.ProductDeleted)
                    viewModelNavigator.pop()
                }
                .onFailure {
                    it.message?.let { message -> showErrorSnackbar(message) }
                }
        }
    }
}