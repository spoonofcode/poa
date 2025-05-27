package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Register
import com.spoonofcode.poa.core.model.RegisterRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class RegisterRepository : GenericCrudRepository<RegisterRequest, Register>(
    resourceName = "register",
    requestSerializer = RegisterRequest.serializer(),
    responseSerializer = Register.serializer(),
    sessionTokenRequired = false,
)