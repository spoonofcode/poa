package com.spoonofcode.feature.notification.data

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.core.data.ext.toZonedInstant
import com.spoonofcode.feature.notification.data.local.LocalNotificationDataSource
import com.spoonofcode.feature.notification.data.mappers.toNotification
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime

class NotificationRepositoryImpl(
    private val remoteNotificationDataSource: RemoteNotificationDataSource,
    private val localNotificationDataSource: LocalNotificationDataSource,
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) : NotificationRepository {


    override suspend fun create(
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime?,
        expirationDateTime: LocalDateTime?
    ): Result<Unit> = remoteNotificationDataSource.create(
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime?.toZonedInstant(),
        expirationDateTime = expirationDateTime?.toZonedInstant(),
    ).map { Unit }

    override suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime?,
        expirationDateTime: LocalDateTime?
    ): Result<Unit> = remoteNotificationDataSource.update(
        notificationId = notificationId,
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime?.toZonedInstant(),
        expirationDateTime = expirationDateTime?.toZonedInstant(),
    ).map { Unit }

    override suspend fun delete(notificationId: String): Result<Unit> =
        remoteNotificationDataSource.delete(
            notificationId = notificationId
        )

    override suspend fun read(notificationId: String): Result<Notification> =
        remoteNotificationDataSource.read(notificationId).map {
            it.toNotification().copy(isRead = isRead(notificationId))
        }

    override suspend fun readAll(): Result<List<Notification>> =
        remoteNotificationDataSource.readAll()
            .map { response ->
                response.map {
                    it.toNotification().copy(isRead = isRead(it.id))
                }
            }

    override suspend fun markAsRead(notificationId: String) {
        localNotificationDataSource.markAsRead(notificationId = notificationId)
    }

    override suspend fun isRead(notificationId: String): Boolean =
        localNotificationDataSource.isRead(notificationId = notificationId)

    override suspend fun getNotificationSettings(): NotificationSettings {
        return withContext(dispatcherProvider.io()) {
            NotificationSettings(
                isNotificationsPersonalizedEnabled = ksafe.get(
                    Keys.Notifications.IS_PERSONALIZED,
                    false
                ),
                isNotificationsFromPartnersEnabled = ksafe.get(
                    Keys.Notifications.IS_PARTNERS_ENABLED,
                    false
                ),
                isNotificationsMyProductSeriesEnabled = ksafe.get(
                    Keys.Notifications.IS_PRODUCT_SERIES_ENABLED,
                    false
                )
            )

        }
    }

    override suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PERSONALIZED, notificationsPersonalizedEnabled)
            ksafe.put(Keys.Notifications.IS_PARTNERS_ENABLED, notificationsFromPartnersEnabled)
            ksafe.put(
                Keys.Notifications.IS_PRODUCT_SERIES_ENABLED,
                notificationsMyProductSeriesEnabled
            )
        }
    }

    private object Keys {
        private const val BASE_PREFIX = "com.spoonofcode"

        object Notifications {
            private const val PREFIX = "$BASE_PREFIX.settings.notifications"
            const val IS_PERSONALIZED = "$PREFIX.personalized"
            const val IS_PRODUCT_SERIES_ENABLED = "$PREFIX.product_series"
            const val IS_PARTNERS_ENABLED = "$PREFIX.partners"
        }
    }
}
