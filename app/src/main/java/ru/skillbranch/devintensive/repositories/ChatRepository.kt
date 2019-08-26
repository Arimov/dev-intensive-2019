package ru.skillbranch.devintensive.repositories

object ChatRepository {
    fun loadChats(): List<Chat> {
        return DataGenerator.generateChats(10)
    }
}