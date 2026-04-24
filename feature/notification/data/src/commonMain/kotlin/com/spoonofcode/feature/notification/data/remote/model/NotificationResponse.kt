package com.spoonofcode.feature.notification.data.remote.model

import com.spoonofcode.core.data.serialization.RFC1123InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class NotificationResponse(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    @SerialName("created_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val createdDataTime: Instant,
    @SerialName("modification_time")
    @Serializable(with = RFC1123InstantSerializer::class)
    val modificationDateTime: Instant,
    @SerialName("delivery_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val deliveryDateTime: Instant? = null,
    @SerialName("expiration_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val expirationDateTime: Instant? = null,
    val state: String,
    val topics: List<String>,
)


val notificationList = listOf(
    NotificationResponse(
        id = "1",
        title = "Nowa wiadomość",
        text = "Otrzymałeś nową wiadomość od administratora.",
        link = "https://example.com/messages/1",
        createdDataTime = Instant.parse("2023-10-01T10:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-01T10:05:00Z"),
        deliveryDateTime = Instant.parse("2023-10-01T10:00:05Z"),
        state = "READ",
        topics = listOf("system", "admin")
    ),
    NotificationResponse(
        id = "2",
        title = "Promocja!",
        text = "Tylko dzisiaj -50% na wszystkie kursy.",
        link = "https://example.com/sale",
        createdDataTime = Instant.parse("2023-10-02T08:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-02T08:00:00Z"),
        expirationDateTime = Instant.parse("2023-10-02T23:59:59Z"),
        state = "UNREAD",
        topics = listOf("marketing", "promotion")
    ),
    NotificationResponse(
        id = "3",
        title = "Aktualizacja aplikacji",
        text = "Dostępna jest nowa wersja aplikacji. Sprawdź co nowego.",
        createdDataTime = Instant.parse("2023-10-03T12:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-03T12:10:00Z"),
        deliveryDateTime = Instant.parse("2023-10-03T12:05:00Z"),
        state = "ARCHIVED",
        topics = listOf("update", "app")
    ),
    NotificationResponse(
        id = "4",
        title = "Przypomnienie o spotkaniu",
        text = "Twoje spotkanie zaczyna się za 15 minut.",
        link = "https://calendar.example.com/event/123",
        createdDataTime = Instant.parse("2023-10-04T09:45:00Z"),
        modificationDateTime = Instant.parse("2023-10-04T09:45:00Z"),
        state = "PENDING",
        topics = listOf("calendar", "meeting")
    ),
    NotificationResponse(
        id = "5",
        title = "Błąd płatności",
        text = "Nie udało się przetworzyć Twojej ostatniej płatności.",
        link = "https://example.com/billing",
        createdDataTime = Instant.parse("2023-10-05T15:30:00Z"),
        modificationDateTime = Instant.parse("2023-10-05T15:35:00Z"),
        state = "URGENT",
        topics = listOf("billing", "error")
    ),
    NotificationResponse(
        id = "6",
        title = "Witaj w klubie!",
        text = "Dziękujemy za dołączenie do naszej społeczności.",
        createdDataTime = Instant.parse("2023-10-06T11:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-06T11:00:00Z"),
        deliveryDateTime = Instant.parse("2023-10-06T11:01:00Z"),
        state = "READ",
        topics = listOf("onboarding")
    ),
    NotificationResponse(
        id = "7",
        title = "Bezpieczeństwo",
        text = "Wykryto logowanie z nowego urządzenia.",
        link = "https://example.com/security/logs",
        createdDataTime = Instant.parse("2023-10-07T20:15:00Z"),
        modificationDateTime = Instant.parse("2023-10-07T20:15:00Z"),
        state = "UNREAD",
        topics = listOf("security", "alert")
    ),
    NotificationResponse(
        id = "8",
        title = "Koniec subskrypcji",
        text = "Twoja subskrypcja wygaśnie za 3 dni.",
        link = "https://example.com/renew",
        createdDataTime = Instant.parse("2023-10-08T07:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-08T07:00:00Z"),
        expirationDateTime = Instant.parse("2023-10-11T07:00:00Z"),
        state = "UNREAD",
        topics = listOf("billing", "subscription")
    ),
    NotificationResponse(
        id = "9",
        title = "Nowy komentarz",
        text = "Użytkownik Jan Kowalski skomentował Twój post.",
        link = "https://example.com/posts/99",
        createdDataTime = Instant.parse("2023-10-09T14:20:00Z"),
        modificationDateTime = Instant.parse("2023-10-09T14:22:00Z"),
        deliveryDateTime = Instant.parse("2023-10-09T14:20:30Z"),
        state = "READ",
        topics = listOf("social", "engagement")
    ),
    NotificationResponse(
        id = "10",
        title = "Przerwa techniczna",
        text = "Dziś o 22:00 planowana jest przerwa techniczna.",
        createdDataTime = Instant.parse("2023-10-10T09:00:00Z"),
        modificationDateTime = Instant.parse("2023-10-10T09:00:00Z"),
        expirationDateTime = Instant.parse("2023-10-10T22:00:00Z"),
        state = "UNREAD",
        topics = listOf("maintenance", "system")
    )
)