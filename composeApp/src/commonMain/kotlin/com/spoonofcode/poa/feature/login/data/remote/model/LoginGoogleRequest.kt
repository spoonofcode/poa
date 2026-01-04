package com.spoonofcode.poa.feature.login.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogleRequest(
    //TODO #13 Switch to POA backend API
//    @SerialName("id_token")
    val googleUserToken: String
)