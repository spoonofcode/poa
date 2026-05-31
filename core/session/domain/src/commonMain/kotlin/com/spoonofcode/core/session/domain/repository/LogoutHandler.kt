package com.spoonofcode.core.session.domain.repository

interface LogoutHandler {
    suspend fun onLogout()
}
