package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.CustomerResponse
import com.spoonofcode.feature.partner.data.model.ProductSeriesResponse
import com.spoonofcode.feature.partner.domain.model.Customer
import com.spoonofcode.feature.partner.domain.model.ProductSeries

fun CustomerResponse.toCustomer(): Customer {
    return Customer(
        id = id,
        email = email,
        name = name,
    )
}