package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: TypeMessage = TypeMessage.TEXT,
            payload: Any?
        ): BaseMessage{
            lastId++
            return when(type){
                TypeMessage.IMAGE -> ImageMessage("$lastId", from, chat, date=date, image=payload as String)
                    else -> TextMessage("$lastId", from, chat, date=date, text = payload as String)
            }
        }
    }
}

enum class TypeMessage{
    TEXT,
    IMAGE
}