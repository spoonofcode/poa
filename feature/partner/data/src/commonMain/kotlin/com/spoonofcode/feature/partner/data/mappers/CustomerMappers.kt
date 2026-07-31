package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.CustomerResponse
import com.spoonofcode.feature.partner.domain.model.Customer

fun CustomerResponse.toCustomer(): Customer {
    return Customer(
        id = id,
        email = email,
        name = name,
    )
}