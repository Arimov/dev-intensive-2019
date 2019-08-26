package ru.skillbranch.devintensive.models.data

data class ChatItem(
    val id: String,
    val avatar: String?,
    val initials: String,
    val title: String,
    val shortDEscription: String?,
    val messageCount: Int = 0,
    val lastMessageDate: String?,
    val isOnline: Boolean = false
)