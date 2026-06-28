package com.spoonofcode.feature.auth.presentation.utils

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.spoonofcode.feature.auth.presentation.BuildKonfig
import com.spoonofcode.feature.auth.presentation.model.GoogleAccount

actual class GoogleAuthUiProvider(
    private val activityContext: Context,
    private val credentialManager: CredentialManager
) {

    @RequiresPermission(Manifest.permission.CREDENTIAL_MANAGER_QUERY_CANDIDATE_CREDENTIALS)
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    actual suspend fun isGoogleAccountAvailable(): Boolean {
        val credentialManager = CredentialManager.create(activityContext)

        return try {
            val prepareResponse = credentialManager.prepareGetCredential(getCredentialRequest())

            prepareResponse.hasCredentialResults(GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)
        } catch (e: Exception) {
            false
        }
    }

    actual suspend fun signIn(): GoogleAccount? = try {
        val credential = credentialManager.getCredential(
            context = activityContext,
            request = getCredentialRequest()
        ).credential
        handleSignIn(credential)
    } catch (exception: Exception) {
        throw exception
    }

    actual suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }

    private fun handleSignIn(credential: Credential): GoogleAccount? = when {
        credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                GoogleAccount(
                    idToken = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName ?: "",
                    profileImageUrl = googleIdTokenCredential.profilePictureUri?.toString()
                )
            } catch (exception: GoogleIdTokenParsingException) {
                throw exception
            }
        }

        else -> null
    }

    private fun getCredentialRequest(): GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(getGoogleIdOption())
        .build()

    private fun getGoogleIdOption(): GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setAutoSelectEnabled(true)
        .setServerClientId(BuildKonfig.GOOGLE_ID_SERVER_CLIENT_ID_WEB)
        .build()
}
