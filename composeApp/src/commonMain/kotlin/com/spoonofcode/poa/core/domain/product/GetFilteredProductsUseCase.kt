package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductFilterRepository
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.product.filter.ProductFilterViewState

class GetFilteredProductsUseCase(
    private val sessionManager: SessionManager,
    private val productRepository: ProductRepository,
    private val productFilterRepository: ProductFilterRepository,
) {
    suspend operator fun invoke(): List<Product> {
        val products = productRepository
            .readProductsOwnedByUser(sessionManager.getSessionUserId())

        val selectedFilter = productFilterRepository.getSelectedFilters()
        return products.filter { product ->
            val matchesNameFilter = selectedFilter.selectedName == null ||
                    selectedFilter.selectedName == ProductFilterViewState.ALL_OPTION_NAME ||
                    product.name == selectedFilter.selectedName
            val matchesCollectionNameFilter = selectedFilter.selectedCollectionName == null ||
                    selectedFilter.selectedCollectionName == ProductFilterViewState.ALL_OPTION_NAME ||
                    product.collectionName == selectedFilter.selectedCollectionName
            matchesNameFilter && matchesCollectionNameFilter
        }
    }
}

