package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.PartnerCategory
import com.spoonofcode.poa.core.model.PartnerCategoryRequest

class PartnerCategoryRepository :
    GenericCrudRepository<PartnerCategoryRequest, PartnerCategory>(
        resourceName = "partnerCategories",
        requestSerializer = PartnerCategoryRequest.serializer(),
        responseSerializer = PartnerCategory.serializer(),
    )