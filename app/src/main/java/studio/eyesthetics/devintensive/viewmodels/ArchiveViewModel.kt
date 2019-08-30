package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class ArchiveViewModel : ViewModel() {

    private val query = mutableLiveData("")
    private val chatRepository = ChatRepository
    private val chats = Transformations.map(chatRepository.loadChats()) { chats ->
        return@map chats.filter { it.isArchived }
            .map { it.toChatItem() }
            .sortedBy { it.id.toInt() }
    }

    fun getChatData(): LiveData<List<ChatItem>> {
        return chats
    }

    fun addToArchive(chatId: String) {
        val chat = chatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(chatId: String) {
        val chat = chatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }

    fun handleSearchQuery(text: String) {
        query.value = text
    }

    fun getLastMessage() = chats.value!!.last().shortDescription

    fun getLastDate() = chats.value!!.last().lastMessageDate

    fun getCountMessages() {
        var count = 0
        for ((i, v) in chats.value!!) {
            Log.d("M_ArchiveViewModel", "i : $i")
            Log.d("M_ArchiveViewModel", "v : $v")
        }
    }

}