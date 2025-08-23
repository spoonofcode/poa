package com.spoonofcode.poa.feature.messagefcm

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

data class MessageFCMViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val fcmUserToken: String = "",
    val fcmRemoteUserToken: String = "",
    val notificationTitle: String = "",
    val notificationBody: String = "",
    val notificationImageUrl: String = "",
    val topics: List<String> = listOf("Sports", "Music", "Travel", "Technology", "Books"),
    val selectedTopicsToSend: List<String> = emptyList(),
    val selectedTopicsToSubscribe: List<String> = emptyList(),
) : BaseViewState(screenState)