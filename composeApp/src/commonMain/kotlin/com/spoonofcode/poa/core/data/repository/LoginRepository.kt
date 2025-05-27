package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Login
import com.spoonofcode.poa.core.model.LoginRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class LoginRepository : GenericCrudRepository<LoginRequest, Login>(
    resourceName = "login",
    requestSerializer = LoginRequest.serializer(),
    responseSerializer = Login.serializer(),
    sessionTokenRequired = false,
)