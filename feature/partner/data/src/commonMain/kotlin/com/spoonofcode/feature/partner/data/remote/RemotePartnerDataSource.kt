package com.spoonofcode.feature.partner.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.partner.data.model.CustomerResponse
import com.spoonofcode.feature.partner.data.model.PartnerRequest
import com.spoonofcode.feature.partner.data.model.PartnerResponse
import com.spoonofcode.feature.partner.data.model.PartnerUserResponse
import com.spoonofcode.feature.partner.data.model.ProductSeriesResponse
import io.ktor.http.HttpMethod

class RemotePartnerDataSource : RemoteBaseDataSource(
    collectionName = "api/partners",
) {
    suspend fun read(partnerId: String): Result<PartnerResponse> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Get,
    )

    suspend fun update(
        partnerId: String,
        name: String,
        partnerCategory: String,
        email: String? = null,
        description: String? = null,
        imageLink: String? = null,
        websiteLink: String? = null,
        instagramLink: String? = null,
        facebookLink: String? = null,
        youtubeLink: String? = null,
        xLink: String? = null,
    ): Result<Unit> = doRequest(
        urlPostfixPath = partnerId,
        method = HttpMethod.Put,
        requestBody = PartnerRequest(
            name = name,
            partnerCategory = partnerCategory,
            email = email,
            description = description,
            imageLink = imageLink,
            websiteLink = websiteLink,
            instagramLink = instagramLink,
            facebookLink = facebookLink,
            youtubeLink = youtubeLink,
            xLink = xLink,
        ),
    )

    suspend fun readAll(): Result<List<PartnerResponse>> = doRequest(
        method = HttpMethod.Get,
    )

    suspend fun readPartnerUsers(partnerId: String): Result<List<PartnerUserResponse>> = doRequest(
        urlPostfixPath = "$partnerId/users",
        method = HttpMethod.Get,
    )

    suspend fun readPartnerSeries(): Result<List<ProductSeriesResponse>> = doRequest(
        urlPostfixPath = "me/series",
        method = HttpMethod.Get,
    )

    suspend fun readPartnerCustomers(): Result<List<CustomerResponse>> = doRequest(
        urlPostfixPath = "me/customers",
        method = HttpMethod.Get,
    )
}