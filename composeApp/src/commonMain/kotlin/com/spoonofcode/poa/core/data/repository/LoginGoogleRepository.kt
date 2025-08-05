package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.LoginGoogle
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
//    resourceName = "api/login/google", //TODO #13 Switch to POA backend API
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)