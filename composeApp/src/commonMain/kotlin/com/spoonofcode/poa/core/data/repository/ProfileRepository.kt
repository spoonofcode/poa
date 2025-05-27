package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.ProfileRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class ProfileRepository : GenericCrudRepository<ProfileRequest, Profile>(
    resourceName = "profile",
    requestSerializer = ProfileRequest.serializer(),
    responseSerializer = Profile.serializer(),
)