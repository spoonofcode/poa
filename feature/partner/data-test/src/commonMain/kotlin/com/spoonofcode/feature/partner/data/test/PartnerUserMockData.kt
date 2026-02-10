package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.domain.model.PartnerUser

object PartnerUserMockData {
    val PARTNER_USER_1 = PartnerUser(
        id = "1",
        firstName = "First Name 1",
        lastName = "Last Name 1",
        email = "user1@email.com"
    )
    val PARTNER_USER_2 = PartnerUser(
        id = "2",
        firstName = "First Name 2",
        lastName = "Last Name 2",
        email = "user2@email.com"
    )
    val PARTNER_USER_3 = PartnerUser(
        id = "3",
        firstName = "First Name 3",
        lastName = "Last Name 3",
        email = "user3@email.com"
    )
    val PARTNER_USERS = listOf(PARTNER_USER_1, PARTNER_USER_2, PARTNER_USER_3)
}