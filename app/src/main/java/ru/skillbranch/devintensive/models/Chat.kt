package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

class Chat(
    val id: String,
    val title: String,
    val members: List<User> = mutableListOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {
    //кол-во непрочитанных сообщений
    fun unreadableMessageCount(): Int {
        return 0
    }

    private fun lastMessageDate(): Date? {
        //TODO return date
        return Date()
    }

    //краткое содержание посл сообщения
    private fun lastMessageShot(): String {
        return "Сообщений нет"
    }

    private fun isSingle(): Boolean = members.size == 1

    fun toChatItem(): ChatItem {
        if (isSingle()) {
            val user = members.first()
            return ChatItem(
                id = id,
                avatar = user.avatar,
                initials = Utils.toInitials(user.firstName, user.lastName) ?: "??",
                title = "${user.firstName ?: ""} ${user.lastName ?: ""}",
                shortDEscription = lastMessageShot(),
                messageCount = unreadableMessageCount(),
                lastMessageDate = lastMessageDate()?.shortFormat(),
                isOnline = user.isOnline
            )
        }else{
            return ChatItem(
                id = id,
                avatar = null,
                initials = "",
                title = title,
                shortDEscription = lastMessageShot(),
                messageCount = unreadableMessageCount(),
                lastMessageDate = lastMessageDate()?.shortFormat(),
                isOnline = false
            )
        }
    }
}



