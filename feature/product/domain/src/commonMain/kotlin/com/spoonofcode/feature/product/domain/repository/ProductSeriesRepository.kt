package com.spoonofcode.feature.product.domain.repository

import com.spoonofcode.feature.product.domain.model.ProductSeries

interface ProductSeriesRepository {
    suspend fun getAllPartnerProductSeries(): Result<List<ProductSeries>>
//    suspend fun getPartnerProductSeries(): Result<ProductSeries>
//    suspend fun getPartnerProductSeriesCustomers(): Result<Customer>
//    suspend fun getPartnerProductSeriesProducts(): Result<ProductSeries>
}