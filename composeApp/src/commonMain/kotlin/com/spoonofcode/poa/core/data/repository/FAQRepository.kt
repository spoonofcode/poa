package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.FAQ
import com.spoonofcode.poa.core.model.FAQRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class FAQRepository : GenericCrudRepository<FAQRequest, FAQ>(
    resourceName = "faqs",
    requestSerializer = FAQRequest.serializer(),
    responseSerializer = FAQ.serializer(),
)