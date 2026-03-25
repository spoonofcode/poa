package com.spoonofcode.feature.product.data

import com.spoonofcode.feature.product.data.mappers.toProductSeries
import com.spoonofcode.feature.product.data.remote.RemoteProductSeriesDataSource
import com.spoonofcode.feature.product.domain.model.ProductSeries
import com.spoonofcode.feature.product.domain.repository.ProductSeriesRepository

class ProductSeriesRepositoryImpl(
    private val remoteProductSeriesDataSource: RemoteProductSeriesDataSource,
) : ProductSeriesRepository {

    override suspend fun getAllPartnerProductSeries(): Result<List<ProductSeries>> =
        remoteProductSeriesDataSource.readAllPartnerProductSeries()
             .map { list -> list.map { it.toProductSeries() } }

//    override suspend fun getPartnerProductSeries(): Result<ProductSeries> {
//        remoteProductSeriesDataSource.readPartnerProductSeries()
//    }
//
//    override suspend fun getPartnerProductSeriesCustomers(): Result<Customer> {
//        remoteProductSeriesDataSource.readPartnerProductSeriesCustomers(seriesName = seriesName)
//    }
}