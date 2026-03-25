package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.CustomerResponse
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.feature.product.data.remote.model.ProductSeriesResponse
import io.ktor.http.HttpMethod

class RemoteProductSeriesDataSource : RemoteBaseDataSource(
    collectionName = "api/series",
) {
    suspend fun readAllPartnerProductSeries(): Result<List<ProductSeriesResponse>> = doRequest(
        method = HttpMethod.Get,
    )

    suspend fun readPartnerProductSeries(seriesName: String): Result<ProductSeriesResponse> = doRequest(
        urlPostfixPath = seriesName,
        method = HttpMethod.Get,
    )

    suspend fun readPartnerProductSeriesCustomers(seriesName: String): Result<CustomerResponse> =
        doRequest(
            urlPostfixPath = "$seriesName/customers",
            method = HttpMethod.Get,
        )

    suspend fun readPartnerProductSeriesProducts(seriesName: String): Result<ProductResponse> =
        doRequest(
            urlPostfixPath = "$seriesName/products",
            method = HttpMethod.Get,
        )
}