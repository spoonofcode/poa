package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.ItemProductUpdateRequest
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.feature.product.data.remote.model.RedirectProductUpdateRequest
import io.ktor.http.HttpMethod

internal class RemoteProductDataSource :
    RemoteBaseDataSource(
        collectionName = "api/products",
    ) {
    suspend fun readProduct(productId: String): Result<ProductResponse> =
        doRequest(
            urlPostfixPath = productId,
            method = HttpMethod.Get,
        )

    suspend fun readUserProducts(): Result<List<ProductResponse>> =
        doRequest(
            method = HttpMethod.Get,
        )

    suspend fun addProductToUser(productId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "$productId/claim",
            method = HttpMethod.Post,
        )

    suspend fun deleteProductFromUser(productId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "$productId/abandon",
            method = HttpMethod.Post,
        )

    suspend fun updateProductItem(
        productId: String,
        customLink: String? = null,
    ): Result<Unit> =
        doRequest(
            urlPostfixPath = productId,
            method = HttpMethod.Put,
            requestBody =
                ItemProductUpdateRequest(
                    customLink = customLink,
                ),
        )

    suspend fun updateProductRedirect(
        productId: String,
        redirectLink: String? = null,
    ): Result<Unit> =
        doRequest(
            urlPostfixPath = productId,
            method = HttpMethod.Put,
            requestBody =
                RedirectProductUpdateRequest(
                    redirectLink = redirectLink,
                ),
        )
}
