package com.spoonofcode.poa.feature.chat

import com.spoonofcode.poa.core.ui.BaseViewState

data class ChatViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val remoteToken: String = "",
    val messageTitle: String = "",
    val messageText: String = "",
    val imageUrl: String = "",
    val firebaseMessageToken: String = "",
    val topics: List<String> = listOf("Sports", "Music", "Travel", "Technology", "Books"),
    val selectedTopicsToSend: List<String> = listOf("Sports"),
    val selectedTopicsToSubscribe: List<String> = emptyList(),
) : BaseViewState()