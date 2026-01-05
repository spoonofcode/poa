package com.spoonofcode.poa.feature.login.data

import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteRegisterDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import com.spoonofcode.poa.feature.login.domain.model.LoginGoogle
import com.spoonofcode.poa.feature.login.domain.model.Register
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val remoteLoginCodeDataSource: RemoteLoginCodeDataSource,
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val remoteLoginGoogleDataSource: RemoteLoginGoogleDataSource,
    private val remoteLoginRequestCodeDataSource: RemoteLoginRequestCodeDataSource,
    private val remoteRegisterDataSource: RemoteRegisterDataSource
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Login> =
        remoteLoginDataSource.create(
            email = email,
            password = password,
        )

    override suspend fun loginWithCode(email: String, loginCode: String): Result<Login> =
        remoteLoginCodeDataSource.create(
            email = email,
            loginCode = loginCode,
        )

    override suspend fun loginWithGoogle(googleUserToken: String): Result<LoginGoogle> =
        remoteLoginGoogleDataSource.create(
            googleUserToken = googleUserToken,
        )

    override suspend fun requestCode(email: String, recaptchaToken: String): Result<Unit> =
        remoteLoginRequestCodeDataSource.create(
            email = email,
            recaptchaToken = recaptchaToken,
        )

    override suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Register> {
        return remoteRegisterDataSource.create(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
        )
    }

}