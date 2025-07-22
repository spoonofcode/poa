package com.spoonofcode.poa.feature.messagefcm

import com.spoonofcode.poa.core.ui.BaseViewState

data class MessageFCMViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val fcmUserToken: String = "",
    val fcmRemoteUserToken: String = "",
    val notificationTitle: String = "",
    val notificationBody: String = "",
    val notificationImageUrl: String = "",
    val topics: List<String> = listOf("Sports", "Music", "Travel", "Technology", "Books"),
    val selectedTopicsToSend: List<String> = emptyList(),
    val selectedTopicsToSubscribe: List<String> = emptyList(),
) : BaseViewState()