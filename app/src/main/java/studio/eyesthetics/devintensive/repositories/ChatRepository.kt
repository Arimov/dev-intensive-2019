package ru.skillbranch.devintensive.repositories

import androidx.lifecycle.MutableLiveData
import ru.skillbranch.devintensive.data.managers.CacheManager
import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.ChatItem

object ChatRepository {
    private val chats = CacheManager.loadChats()

    fun loadChats(): MutableLiveData<List<Chat>> {
        return chats
    }

    fun update(chat: Chat) {
        val copy = chats.value!!.toMutableList()
        val find = chats.value!!.indexOfFirst { it.id == chat.id }
        if (find == -1) return
        copy[find] = chat
        chats.value = copy

    }

    fun find(chatId: String): Chat? {
        val find = chats.value!!.indexOfFirst { it.id == chatId }
        return chats.value!!.getOrNull(find)
    }
}