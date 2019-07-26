package ru.skillbranch.devintensive.models

import android.provider.ContactsContract

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val reposiory: String,
    val rating: Int = 0,
    val respect: Int = 0
) {

    val nickName: String = "John Doe" //Implement me
    val rank: String = "Junior Android Developer"

    fun toMap(): Map<String, Any> = mapOf(
        "nickname" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "reposiory" to reposiory,
        "rating" to rating,
        "respect" to respect
    )
}