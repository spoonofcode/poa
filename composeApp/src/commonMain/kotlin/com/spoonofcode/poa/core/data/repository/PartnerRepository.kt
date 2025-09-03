package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest

class PartnerRepository : GenericCrudRepository<PartnerRequest, Partner>(
    resourceName = "partners",
    requestSerializer = PartnerRequest.serializer(),
    responseSerializer = Partner.serializer(),
)