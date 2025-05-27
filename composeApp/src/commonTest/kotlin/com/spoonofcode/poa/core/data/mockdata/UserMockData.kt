package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.core.model.User

object UserMockData {
    val USER_1 = User(
        id = 1,
        firstName = "First Name 1",
        lastName = "Last Name 1",
        email = "user1@email.com"
    )
    val USER_2 = User(
        id = 2,
        firstName = "First Name 2",
        lastName = "Last Name 2",
        email = "user2@email.com"
    )
    val USER_3 = User(
        id = 3,
        firstName = "First Name 3",
        lastName = "Last Name 3",
        email = "user3@email.com"
    )
    val USERS = listOf(USER_1, USER_2, USER_3)
}