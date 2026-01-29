package com.spoonofcode.feature.auth.data

import com.spoonofcode.feature.auth.data.mappers.toLogin
import com.spoonofcode.feature.auth.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val remoteLoginCodeDataSource: RemoteLoginCodeDataSource,
    private val remoteLoginGoogleDataSource: RemoteLoginGoogleDataSource,
    private val remoteLoginRequestCodeDataSource: RemoteLoginRequestCodeDataSource,
) : LoginRepository {

    override suspend fun loginWithCode(email: String, loginCode: String): Result<Login> =
        remoteLoginCodeDataSource.create(
            email = email,
            loginCode = loginCode,
        ).map { response -> response.toLogin() }

    override suspend fun loginWithGoogle(googleUserToken: String): Result<Login> =
        remoteLoginGoogleDataSource.create(
             googleUserToken = googleUserToken,
         ).map { response -> response.toLogin() }

    override suspend fun requestCode(email: String, recaptchaToken: String): Result<Unit> =
        remoteLoginRequestCodeDataSource.create(
            email = email,
            recaptchaToken = recaptchaToken,
        )
}