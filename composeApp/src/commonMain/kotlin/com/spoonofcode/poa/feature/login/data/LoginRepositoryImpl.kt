package com.spoonofcode.poa.feature.login.data

import com.spoonofcode.poa.feature.login.data.mappers.toLogin
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.poa.feature.login.data.remote.ktor.KtorRemoteLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.model.Login
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val ktorRemoteLoginGoogleDataSource: KtorRemoteLoginGoogleDataSource,
    private val remoteLoginCodeDataSource: RemoteLoginCodeDataSource,
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val remoteLoginGoogleDataSource: RemoteLoginGoogleDataSource,
    private val remoteLoginRequestCodeDataSource: RemoteLoginRequestCodeDataSource,
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Login> =
        remoteLoginDataSource.create(
            email = email,
            password = password,
        ).map { response -> response.toLogin() }

    override suspend fun loginWithCode(email: String, loginCode: String): Result<Login> =
        remoteLoginCodeDataSource.create(
            email = email,
            loginCode = loginCode,
        ).map { response -> response.toLogin() }

    override suspend fun loginWithGoogle(googleUserToken: String): Result<Login> =
        remoteLoginGoogleDataSource.create(
            googleUserToken = googleUserToken,
        ).map { response -> response.toLogin() }

    override suspend fun loginWithGoogleKtor(googleUserToken: String): Result<Login> =
        ktorRemoteLoginGoogleDataSource.create(
            googleUserToken = googleUserToken,
        ).map { response -> response.toLogin() }

    override suspend fun requestCode(email: String, recaptchaToken: String): Result<Unit> =
        remoteLoginRequestCodeDataSource.create(
            email = email,
            recaptchaToken = recaptchaToken,
        )
}