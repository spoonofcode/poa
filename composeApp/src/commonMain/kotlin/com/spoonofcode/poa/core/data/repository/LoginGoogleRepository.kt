package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.LoginGoogle
import com.spoonofcode.poa.core.model.LoginGoogleRequest

class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)