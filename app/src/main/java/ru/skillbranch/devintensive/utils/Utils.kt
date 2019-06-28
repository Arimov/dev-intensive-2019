package ru.skillbranch.devintensive.utils

object Utils {
    fun ParseFullName(fullName : String?):Pair<String?, String?>{
        var parts : List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        if(firstName.isNullOrEmpty() || firstName.isNullOrBlank()) {
            firstName = null
        }
        var lastName = parts?.getOrNull(1)
        if(lastName.isNullOrEmpty() || lastName.isNullOrBlank()) {
            lastName = null
        }
        return firstName to lastName
    }
}
