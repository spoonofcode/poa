package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.model.NotificationRequest
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class NotificationRepository : GenericCrudRepository<NotificationRequest, Notification>(
    resourceName = "notifications",
    requestSerializer = NotificationRequest.serializer(),
    responseSerializer = Notification.serializer(),
)