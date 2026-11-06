package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.CustomerResponse
import com.spoonofcode.feature.partner.domain.model.Customer

internal fun CustomerResponse.toCustomer(): Customer =
    Customer(
        id = id,
        email = email,
        name = name,
    )
