package ru.skillbranch.devintensive.utils

object Utils {
    fun ParseFullName(fullName: String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")
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

    fun toInitials(firstName: String?, lastName: String?): String?{
        val result: String
        var firstSymbol = ""
        var lastSymbol = ""
        if(!firstName.isNullOrEmpty() && !firstName.isNullOrBlank()){
            firstSymbol = firstName.substring(0,1).toUpperCase()
        }
        if(!lastName.isNullOrEmpty() && !lastName.isNullOrBlank()){
            lastSymbol = lastName.substring(0,1).toUpperCase()
        }
        result = firstSymbol + lastSymbol
        if(result == ""){
            return null
        }
        else{
            return result
        }
    }
}
