package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.ProductFilterRepository
import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.feature.search.filter.FilterViewState
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

class GetFilteredProductsUseCase(
    private val productRepository: ProductRepository,
    private val productFilterRepository: ProductFilterRepository,
) {
    suspend operator fun invoke(): List<Product> {
        // TODO Change with data from API
//        val products = productRepository.readAll()
        val products = getMockedProducts()

        val selectedFilter = productFilterRepository.getSelectedFilters()
        return products.filter { product ->
            val matchesNameFilter = selectedFilter.selectedName == null ||
                    selectedFilter.selectedName == FilterViewState.ALL_OPTION_NAME ||
                    product.name == selectedFilter.selectedName
            val matchesCollectionNameFilter = selectedFilter.selectedCollectionName == null ||
                    selectedFilter.selectedCollectionName == FilterViewState.ALL_OPTION_NAME ||
                    product.collectionName == selectedFilter.selectedCollectionName
            matchesNameFilter && matchesCollectionNameFilter
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun getMockedProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Product 1",
                description = "Description product 1",
                tagId = "04A971E2E51090",
                collectionName = "Collection 1",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 1,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
            Product(
                id = 2,
                name = "Product 2",
                description = "Description product 2",
                tagId = "04A972E2E52090",
                collectionName = "Collection 2",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 2,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
            Product(
                id = 3,
                name = "Product 3",
                description = "Description product 3",
                tagId = "04A973E3E53090",
                collectionName = "Collection 3",
                websiteLink = "https://beautysaute.pl/",
                customLink = "https://beautysaute.pl/",
                ownerUser = User(
                    id = 3,
                    firstName = "Bartosz",
                    lastName = "Luczak",
                    email = "bartosz.luczak@gmail.com"
                ),
            ),
        )
    }
}

